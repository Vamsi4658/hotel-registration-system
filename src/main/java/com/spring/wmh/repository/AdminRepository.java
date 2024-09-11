package com.spring.wmh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.wmh.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {


	List<Admin> findByadminUserName(String adminUserName);
}
