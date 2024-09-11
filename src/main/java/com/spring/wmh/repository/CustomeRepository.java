package com.spring.wmh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.wmh.entity.Customer;

@Repository
public interface CustomeRepository extends JpaRepository<Customer, Integer> {

}
