package com.spring.wmh.service;

import java.util.List;

import com.spring.wmh.DTO.RoomBookingDTO;




public interface RoomBookingService {

	
	public Object saveBooking(int customerId, int roomTypeId, RoomBookingDTO bookingDTO);
	
	public List<Object> getAllBookings();
	
	public Object getbookingById(int id);
	
	public Object updateBookIngById(int id, RoomBookingDTO bookingDTO);
	
	public String deleteBookingInfoById(int id);
}
