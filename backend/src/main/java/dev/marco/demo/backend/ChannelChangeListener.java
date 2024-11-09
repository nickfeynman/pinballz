package dev.marco.demo.backend;

import dev.marco.demo.backend.webcams.WebcamSwitcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ChannelChangeListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final RestClient restClient;
    private final String apiUrl;
    private final WebcamSwitcher webcamSwitcher = new WebcamSwitcher();

    public ChannelChangeListener(@Value("${matchplay.api.url}") String apiUrl) {
        this.restClient = RestClient.create();
        this.apiUrl = apiUrl;
    }

    @Async
    @EventListener
    public void handleChannelChangedEvent(ChannelChangedEvent event) {
        logger.info("Channel changed event received.  New channel: " + event.getNewChannelId());


        logger.info("Calling matchplay app {}/active-pin-id with channel {}", apiUrl, event.getNewChannelId());
        try {
            String response = restClient.post()
                    .uri(apiUrl + "/active-pin-id")
                    .body(event.getNewChannelId())
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            logger.error("Could not call {} with channelId {}", apiUrl , event.getNewChannelId());
        }

        logger.info("Going to switch the webcam");

        webcamSwitcher.switchCamera(event.getNewChannelId());

        logger.info("handleChannelChangedEvent completed");

    }
}