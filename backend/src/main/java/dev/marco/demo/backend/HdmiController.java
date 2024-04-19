package dev.marco.demo.backend;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ScreenSaver screenSaver;

    @Autowired
    private HdmiSwitch hdmiSwitch;

    @GetMapping("/hdmi/{id}")
    public Map getHdmiData(@PathVariable("id") int id) {

        hdmiSwitch.setChannel(id);
        screenSaver.stop();
        screenSaver.start();

        return Map.of("text", "Received request for HDMI device with ID: " + id);
    }
}
