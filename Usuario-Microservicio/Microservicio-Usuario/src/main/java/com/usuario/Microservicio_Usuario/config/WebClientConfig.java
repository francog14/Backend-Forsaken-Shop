package com.usuario.Microservicio_Usuario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    // rol
    @Bean
    public WebClient rolWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:7075/").build();
    }

}
