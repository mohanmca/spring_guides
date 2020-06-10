package com.nikias.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SleuthController {

    Logger logger = LoggerFactory.getLogger(SleuthController.class);

    @GetMapping("/")
    public String hello() {
        logger.info("Hello Sleuth!");
        return "Hello Success Sleuth! - - <br/>" + java.time.Instant.now().toString();
    }
}
