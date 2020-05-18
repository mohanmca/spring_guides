package com.example.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Customer implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(Customer.class);

//    @Value("${customer.id}")
    private long id;
//    @Value("${customer.firstName}")
    private String firstName;
//    @Value("${customer.lastName}")
    private String  lastName;

    public Customer(@Value("${customer.id}") long id,  @Value("${customer.firstName}")  String firstName, @Value("${customer.lastName}") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void init() {
        log.info("Initialization init happens!");
    }


    @PostConstruct
    public void postConstruct() {
        log.info("postConstruct happens!");
    }


    public void afterPropertiesSet() {
        log.info("afterPropertiesSet happens!");
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    // getters & setters omitted for brevity
}