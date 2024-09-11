package com.spring.wmh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.wmh.DTO.AdminDTO;
import com.spring.wmh.entity.Admin;
import com.spring.wmh.service.AdminService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add")
	public ResponseEntity<AdminDTO> addAdmin(@RequestBody AdminDTO adminDto){
		
		AdminDTO dto = adminService.addAdmin(adminDto);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/get/Admins")
	public ResponseEntity<List<AdminDTO>> showAllAdmins() {
//		List<AdminDTO> admins = adminService.getAllAdmins();
		
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
}
