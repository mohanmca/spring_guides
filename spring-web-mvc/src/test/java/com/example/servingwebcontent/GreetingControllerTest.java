package com.example.servingwebcontent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@WebMvcTest
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGrettingController() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/greeting").param("name", "test"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(mvc -> mvc.getResponse().getContentAsString().contains("<p  >Hello, test!</p>") )
        ;

    }
}