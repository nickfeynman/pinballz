package dev.marco.demo.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ChannelChangeListener {

    private final RestClient restClient;
    private final String apiUrl;

    public ChannelChangeListener(@Value("${matchplay.api.url}") String apiUrl) {
        this.restClient = RestClient.create();
        this.apiUrl = apiUrl;
    }

    @Async
    @EventListener
    public void handleChannelChangedEvent(ChannelChangedEvent event) {
        System.out.println("Channel changed event received.  New channel: " + event.getNewChannelId());

        String response = restClient.post()
                .uri(apiUrl + "/active-pin-id")
                .body(event.getNewChannelId())
                .retrieve()
                .body(String.class);

        System.out.println("API Response: " + response);

    }
}