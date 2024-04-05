package dev.marco.demo.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HexFormat;
import java.util.Map;

@RestController
public class HdmiController {

    @GetMapping("/hdmi/{id}")
    public Map getHdmiData(@PathVariable("id") int id) {

        String hexString = "0" + id;
        byte[] byteArray = HexFormat.of().parseHex(hexString);

        String host = "192.168.1.10";
        int port = 5000;
        byte[] data = {(byte) 0xAA, (byte) 0xBB, 0x03, 0x01, byteArray[0], (byte) 0xEE};

        try (Socket socket = new Socket(host, port)) {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Map.of("text", "Received request for HDMI device with ID: " + id);
    }
}
