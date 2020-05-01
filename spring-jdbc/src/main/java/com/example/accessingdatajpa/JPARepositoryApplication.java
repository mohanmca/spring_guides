package com.example.accessingdatajpa;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.stream.StreamSupport;

@SpringBootApplication
@EnableJpaRepositories
public class JPARepositoryApplication {

    private final Logger log = LoggerFactory.getLogger(JPARepositoryApplication.class);

    @Autowired
    private CustomerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(JPARepositoryApplication.class);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch an individual customer by ID
            Optional<Customer> optCustomer = repository.findById(1l);
            optCustomer.ifPresent(cus -> log.debug("Customer found was {} ", cus.toString()));

            log.info("Find all customers");
            StreamSupport.stream(repository.findAll().spliterator(), false).forEach(customer -> log.debug(customer.toString()));
            StreamSupport.stream(repository.findByLastName("Dessler").spliterator(), false).forEach(customer -> log.debug(customer.toString()));

        };
    }
}
