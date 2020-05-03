package com.example.securingweb;

import com.example.uploadingfiles.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class WebSecurityDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSecurityDemoApplication.class, args);
    }

}
