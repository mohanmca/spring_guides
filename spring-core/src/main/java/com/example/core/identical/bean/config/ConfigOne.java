package com.example.core.identical.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigOne {
    @Bean
    public String example() {
        return "Example1";
    }
}
