package com.spring.wmh.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CustomerDTO {
	
	@NotNull(message = "customer firstname required")
	@Size(min = 2, message = "firstname should not empty")
	private String customerFirstName;
	
	@NotNull(message = "customer lastname required")
	@Size(min = 1, message = "lastname should not empty")
	private String customerLastName;
	
	@NotNull(message = "customer email required")
	@Size(min = 1, message = "email should not empty")
	private String customerEmail;
	
	@NotNull(message = "customer contact number required")
	@Size(min = 1, message = "contact should not empty")
	private String contactNumber;
	
	@NotNull(message = "customer dob required")
	@Size(min = 1, message = "dob should not empty")	
	private String dob;
	
	@NotNull(message = "customer address1 required")
	@Size(min = 1, message = "address1 should not empty")	
	private String address1;
	
	private String address2;
	
	@NotNull(message = "customer city required")
	@Size(min = 1, message = "city should not empty")	
	private String city;
	
	@NotNull(message = "customer pincode required")
	@Size(min = 1, message = "pincode should not empty")	
	private String pincode;

	private AdminDTO admin;
	
}
