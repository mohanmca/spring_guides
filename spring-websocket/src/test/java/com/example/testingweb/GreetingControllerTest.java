package com.example.testingweb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GreetingControllerTest {
    @Autowired private GreetingController controller;

    @Test
    public void contextLoads() {
        Assertions.assertThat(controller).isNotNull();
    }
}