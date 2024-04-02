package com.petabyte.digitalpersonaweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Разрешить доступ из любого источника
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешить определенные методы запросов
                .allowedHeaders("Content-Type", "Authorization"); // Разрешить определенные заголовки запросов
    }
}
