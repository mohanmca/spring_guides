package com.example.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoreBeanApplication {
    private static final Logger log = LoggerFactory.getLogger(CoreBeanApplication.class);

    @Autowired
    private Customer customer;


    public static void main(String[] args) {
        SpringApplication.run(CoreBeanApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            log.info("~~~Core bean initialization - inside the usage of the bean");
            log.info("bean - {} ~~~", customer.toString());
        };
    }

}
