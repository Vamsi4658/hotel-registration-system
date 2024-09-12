package com.spring.wmh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.wmh.DTO.CustomerDTO;
import com.spring.wmh.DTO.RoomBookingDTO;
import com.spring.wmh.DTO.RoomTypeDTO;
import com.spring.wmh.service.RoomBookingService;

@CrossOrigin
@RestController
@RequestMapping("/room/booking")
public class RoomBookingTypeController {

	@Autowired
	RoomBookingService roomBookingService;
	
	@PostMapping("/{id}/addcustomer")
	public Object saveCustomer(int customerId, int roomTypeId,@RequestBody RoomBookingDTO RoomBookingDTO) {
		return roomBookingService.saveBooking(customerId,roomTypeId, RoomBookingDTO) ;
	}
	
	@GetMapping("/getall")
	public List<Object> getAllCustomer() {
		return roomBookingService.getAllBookings();
	}
	
	@PostMapping("/get/{id}")
	public Object getCustomerById(@PathVariable int id ) {
		return roomBookingService.getbookingById(id);
	}
	
//	@PostMapping("/update/{id}")
//	public Object updateById(int customerId, int roomTypeId, @RequestBody RoomBookingDTO bookingDTO) {
////		return roomBookingService.updateBookIngById(customerId,roomTypeId, bookingDTO);
//	}
//	
	@PostMapping("/delete/{id}")
	public String removeById(@PathVariable int id) {
		return roomBookingService.deleteBookingInfoById(id);
	}
}
