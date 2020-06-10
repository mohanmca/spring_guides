package com.example.restservice;

import io.micrometer.core.annotation.Timed;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Endpoint(enableByDefault = true, id = "greetingControler")
@Timed
public class GreetingController implements HealthIndicator {

    private final Random random = new Random();
    private final AtomicLong counter = new AtomicLong();

    private final static String template = "Hello, %s!";

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @ReadOperation
    public long getTime(){
        return System.currentTimeMillis();
    }

    @Override
    public Health health() {
        return random.nextBoolean() ? Health.down().withException(new RuntimeException()).build() : Health.up().build();
    }
}
