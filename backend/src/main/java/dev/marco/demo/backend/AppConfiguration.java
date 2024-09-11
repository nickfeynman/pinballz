package dev.marco.demo.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Configuration
@EnableScheduling
public class AppConfiguration {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            try {
                factory.setAddress(InetAddress.getByName("192.168.1.161")); // Replace with your desired IP address
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            factory.setPort(8080); // You can also set the port here if needed
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/hdmi/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }

    @Bean
    public RestClient restClient(RestClient.Builder restClientBuilder) {
        return  restClientBuilder.baseUrl("http://localhost:8081")
                .defaultHeaders(h -> {
                    h.setContentType(MediaType.APPLICATION_JSON);
                    h.setAccept(List.of(MediaType.APPLICATION_JSON));
                })
                .build();
    }


    @Bean
    public HdmiSwitch hdmiSwitch() {
        boolean testMode = false;
        return new HdmiSwitch(testMode);
    }
}
