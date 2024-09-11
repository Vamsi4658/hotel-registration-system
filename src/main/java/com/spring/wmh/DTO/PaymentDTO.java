package com.spring.wmh.DTO;

import java.util.Date;

import com.spring.wmh.entity.RoomBooking;

import lombok.Data;

@Data
public class PaymentDTO {

	private int paymentId;
	private String paymentMode;
	private Date paymentDate;
	private String paymentStatus;
	private long cardNumber;
	private int cvv;
	private RoomBooking roomBooking;
	
}
