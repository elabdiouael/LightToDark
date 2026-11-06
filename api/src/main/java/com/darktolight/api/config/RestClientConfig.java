package com.darktolight.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        // Hada client moderne dyal Spring 3.2 (fluent API)
        return RestClient.builder().build();
    }
}