package com.autoagent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${nasa.api.base-url}")
    private String nasaBaseUrl;

    @Bean
    public WebClient nasaWebClient() {
        return WebClient.builder()
                .baseUrl(nasaBaseUrl)
                .build();
    }

}
