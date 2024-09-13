package com.spring.wmh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.wmh.entity.Customer;

@Repository
public interface CustomeRepository extends JpaRepository<Customer, Integer> {

	
	@Query("SELECT c FROM Customer c WHERE c.customerLastName = :lastName OR c.contactNumber = :contact")
    List<Customer> findByLastNameOrContact(@Param("lastName") String lastName, @Param("contact") String contact);
}
