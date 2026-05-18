package com.mensajeria.Microservicio_Mensajeria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    // usuario
    @Bean
    public WebClient usuarioWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:7076/").build();
    }

}
