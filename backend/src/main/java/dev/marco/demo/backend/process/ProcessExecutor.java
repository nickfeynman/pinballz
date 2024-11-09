package dev.marco.demo.backend.process;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ProcessExecutor {

    public ProcessResult execute(String command) {
        String[] commands;
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            commands = new String[] { "cmd.exe", "/c", command };
        }
        else {
            commands = new String[] { "bash", "-c", command };
        }

        Path tempDir = null;
        try {
            tempDir = Files.createTempDirectory("tempDirPrefix");

            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            processBuilder.directory(tempDir.toFile());


            System.out.println("Executing: " + StringUtils.arrayToDelimitedString(commands, " "));

            Process process = processBuilder.start();
            Optional<String> stderr = readStringFromInputStream(process.getInputStream());
            Optional<String> stdout = readStringFromInputStream(process.getErrorStream());

            boolean exited = process.waitFor(60, TimeUnit.SECONDS);
            if (!exited) {
                System.err.println("Wait time of 60 seconds elapsed, command was not executed");
            } else {
                System.out.println("Process exited with code " + process.exitValue());
            }
            return new ProcessResult(stdout.get(), stderr.get(), process.exitValue());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Delete the temporary directory if it was created
            if (tempDir != null) {
                try {
                    Files.deleteIfExists(tempDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private Optional<String> readStringFromInputStream(InputStream input) {
        final String newline = System.getProperty("line.separator");
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return Optional.of(buffer.lines().collect(Collectors.joining(newline)));
        }
        catch (IOException ex) {
            System.err.println("Could not read command output: " + ex.getMessage());
        }
        return Optional.empty();
    }

}
