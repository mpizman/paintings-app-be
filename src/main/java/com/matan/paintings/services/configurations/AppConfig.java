package com.matan.paintings.services.configurations;

import com.matan.paintings.services.implementations.GetPaintingByIdService;
import com.matan.paintings.services.implementations.PostPaintingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PostPaintingService postPaintingService() {
        return new PostPaintingService();
    }

    @Bean
    public GetPaintingByIdService getPaintingByIdService() {
        return new GetPaintingByIdService();
    }
}
