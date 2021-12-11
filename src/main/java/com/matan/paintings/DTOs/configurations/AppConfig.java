package com.matan.paintings.DTOs.configurations;

import com.matan.paintings.DTOs.implemenatations.PaginationDTO;
import com.matan.paintings.DTOs.implemenatations.SortDTO;
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
}
