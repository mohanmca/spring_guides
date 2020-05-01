package com.example.accessingdatajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public List<Customer> findByLastName(String lastName);
    public List<Customer> findByFirstName(String lastName);
}
