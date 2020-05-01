package com.example.consumingrest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConsumingRestApplicationTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() {
        assertThat(restTemplate).isNotNull();
    }

}