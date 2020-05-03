package com.example.testingweb;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(controllers = HelloGreetingController.class)
class ControllerStandaloneTest {
    @Autowired private MockMvc mockMvc;
    @MockBean
    private GreetingService service;

    @Test
    public void contextLoads() throws Exception {

        Mockito.when(service.greet()).thenReturn("Hello, Mock!");

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/test-greet"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("Hello, Mock!") ));

    }
}