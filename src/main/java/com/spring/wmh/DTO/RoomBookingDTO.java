package com.spring.wmh.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class RoomBookingDTO {

	
	private String fromDate;
	private String toDate;
	private byte noOfPeople;
	private String bookedOn;
	
	private RoomTypeDTO room;
	
	private CustomerDTO customerDto;
	
}
