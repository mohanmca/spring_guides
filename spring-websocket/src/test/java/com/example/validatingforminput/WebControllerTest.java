package com.example.validatingforminput;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class WebControllerTest {

    @Autowired private MockMvc mockMvc;

    @Test
    public void checkPerson() throws Exception {
        MockHttpServletRequestBuilder createPerson = MockMvcRequestBuilders.post("/").param("age", "20");
        mockMvc.perform(createPerson).andExpect(MockMvcResultMatchers.model().hasErrors());
    }

}