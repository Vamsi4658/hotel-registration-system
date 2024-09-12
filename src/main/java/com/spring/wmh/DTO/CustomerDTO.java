package com.spring.wmh.DTO;

import com.spring.wmh.entity.Admin;

import lombok.Data;


@Data
public class CustomerDTO {
	
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	private String contactNumber;
	private String dob;
	private String address1;
	private String address2;
	private String city;
	private String pincode;
	private AdminDTO admin;
	
}
