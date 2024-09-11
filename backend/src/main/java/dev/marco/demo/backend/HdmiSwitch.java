package dev.marco.demo.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HexFormat;

@Component
public class HdmiSwitch implements ApplicationEventPublisherAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private boolean testMode;

    private ApplicationEventPublisher eventPublisher;

    public HdmiSwitch() {
        this.testMode = false;
    }

    public HdmiSwitch(boolean testMode) {
        this.testMode = testMode;
    }

    public void setChannel(int id)  {
        logger.info("Changing to HDMI input:  " + id);
        if (testMode) {
            logger.info("Simulating changing to hdmi input " + id);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("publishing event");
            eventPublisher.publishEvent(new ChannelChangedEvent(this, id));
        } else {
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
            logger.info("publishing event");
            eventPublisher.publishEvent(new ChannelChangedEvent(this, id));
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
