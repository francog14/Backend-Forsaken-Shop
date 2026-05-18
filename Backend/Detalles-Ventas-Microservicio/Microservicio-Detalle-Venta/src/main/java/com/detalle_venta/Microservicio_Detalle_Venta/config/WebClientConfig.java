package com.detalle_venta.Microservicio_Detalle_Venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    // venta
    @Bean
    public WebClient ventaWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:7077/").build();
    }

    // prenda
    @Bean
    public WebClient prendaWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:7580/").build();
    }

}
