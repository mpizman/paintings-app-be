package com.matan.paintings.configuration;

import com.matan.paintings.DTOs.implemenatations.PaginationDTO;
import com.matan.paintings.DTOs.implemenatations.SortDTO;
import com.matan.paintings.services.implementations.GetPaintingByIdService;
import com.matan.paintings.services.implementations.GetPaintingsService;
import com.matan.paintings.services.implementations.PostPaintingService;
import com.matan.paintings.services.mappers.implementation.PaginationInputToPaginationDTO;
import com.matan.paintings.services.mappers.implementation.SortInputToSortDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SortDTO sortDTO() {
        return new SortDTO();
    }

    @Bean
    public PaginationDTO paginationDTO() {
        return new PaginationDTO();
    }

    @Bean
    public PostPaintingService postPaintingService() {
        return new PostPaintingService();
    }

    @Bean
    public GetPaintingByIdService getPaintingByIdService() {
        return new GetPaintingByIdService();
    }

    @Bean
    public GetPaintingsService getPaintingsService() {
        return new GetPaintingsService();
    }

    @Bean
    public SortInputToSortDTO sortInputToSortDTO() {
        return new SortInputToSortDTO();
    }

    @Bean
    public PaginationInputToPaginationDTO paginationInputToPaginationDTO() {
        return new PaginationInputToPaginationDTO();
    }
}
