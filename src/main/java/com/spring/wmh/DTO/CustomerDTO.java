package com.spring.wmh.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import lombok.Data;

@Data
public class CustomerDTO {

	private int customerId;
	private String customerFirstName;
	private String customerLastName;
	private String contactNumber;
	private String dob;
	private String address1;
	private String address2;
	private String city;
	private String pincode;
	private String customerCreatedOn;
	private List<RoomBookingDTO> roomBooking = new ArrayList<RoomBookingDTO>();
	private AdminDTO admin;
	
}
