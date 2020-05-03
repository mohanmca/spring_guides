package com.example.testingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
public class HelloGreetingController {

    @Autowired
    private GreetingService greetingService;

    @RequestMapping("/test-greet")
    public @ResponseBody String greeting() {
        return  greetingService.greet();
    }
}
