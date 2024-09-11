package com.spring.wmh.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class RoomBookingDTO {

	private int booking_id;
	private Date fromDate;
	private Date toDate;
	private byte noOfPeople;
	private Date bookedOn;
	private RoomTypeDTO room;	
	private List<PaymentDTO> paymentDtos = new ArrayList<PaymentDTO>();	
	private CustomerDTO customerDto;
	
}
