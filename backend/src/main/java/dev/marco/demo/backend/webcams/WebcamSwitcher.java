package dev.marco.demo.backend.webcams;

import dev.marco.demo.backend.process.ProcessExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamSwitcher {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void switchCamera(int id) {
        if (false) {
            logger.info("pin id to switch to " + id);
            int webcamId = calculateWebcamFromPinId(id);
            logger.info("webcam id to switch to " + webcamId);
            ProcessExecutor processExecutor = new ProcessExecutor();
            processExecutor.execute("/home/pin/switch-webcam.sh " + webcamId);
            logger.info("switched");
        }
    }

    private int calculateWebcamFromPinId(int id) {
        return switch (id) {
            case 1 ->  // Star Trek -TNG
                    1;  // Indianapolis 500
            case 2, 3 ->  // Whirlwind
                    2;  // Pulp Fiction
            case 4, 5 ->  // Congo
                    3;  // Kong
            case 6, 7 ->  // Walking Dead Wizard
                    4;
            default ->
                // Default case for unknown IDs or NYC PINS (ID 8)
                    1; // You can change this default value as needed
        };
    }

}
