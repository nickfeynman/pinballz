package dev.marco.demo.backend.process;

public record ProcessResult(String stdout, String stderr, int exitCode) {
}
