package com.example.relational;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class RelationalApplication {
    private static final Logger log = LoggerFactory.getLogger(RelationalApplication.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(RelationalApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            log.info("JDBC template");
            jdbcTemplate.execute("Drop table customer if exists");
            jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

            // Split up the array of whole names into an array of first/last names
            List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                    .map(name -> name.split(" "))
                    .collect(Collectors.toList());
            splitUpNames.forEach(names -> log.info(String.format("Insert customer record %s, %s", names)));

            jdbcTemplate.batchUpdate("Insert into customers(first_name, last_name) VALUES (?,?)", splitUpNames);

            RowMapper<Customer> customerRowMapper = (ResultSet rs, int rownum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"));

            List<Customer> results = jdbcTemplate.query("Select id, first_name, last_name from customers where first_name = ?", new Object[]{"Josh"}, customerRowMapper);

            results.forEach(customer -> log.info(customer.toString()));
        };
    }

}
