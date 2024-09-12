package com.spring.wmh.serviceImp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.wmh.DTO.CustomerDTO;
import com.spring.wmh.DTO.RoomBookingDTO;
import com.spring.wmh.DTO.RoomTypeDTO;
import com.spring.wmh.entity.Customer;
import com.spring.wmh.entity.RoomBooking;
import com.spring.wmh.entity.RoomsType;
import com.spring.wmh.repository.CustomeRepository;
import com.spring.wmh.repository.RoomBookingRepository;
import com.spring.wmh.repository.RoomsTypeRepository;
import com.spring.wmh.service.RoomBookingService;

@Service
public class RoomBookingServiceImp implements RoomBookingService{

	
	@Autowired
	private RoomBookingRepository roomBookingRepository;
	@Autowired
	private CustomeRepository customeRepository;
	@Autowired
	private RoomsTypeRepository roomsTypeRepository;

	
	
	@Override
	public Object saveBooking(int customerId, int roomTypeId, RoomBookingDTO bookingDTO) {
		
		Map<String, Object> map = new HashMap<>();
		
		RoomBookingDTO bookingDto =new RoomBookingDTO();
		
		Customer customer = customeRepository.findById(customerId).orElse(null);
		RoomsType roomsType = roomsTypeRepository.findById(roomTypeId).orElse(null);
		
		// String to date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		if(customer!=null && roomsType!=null) {
	
			RoomBooking booking = new RoomBooking();
			
			booking.setCustomer(customer);
			booking.setRoom(roomsType);
			booking.setCheckInDate(LocalDate.parse(bookingDTO.getFromDate(), formatter));
			booking.setCHeckOutDate(LocalDate.parse(bookingDTO.getToDate(), formatter));
			booking.setBookedOn(LocalDate.now());
 			booking.setNoOfPeople(bookingDTO.getNoOfPeople());
			
			roomBookingRepository.save(booking);
			
//			CustomerDTO customerDTO = new CustomerDTO();
//			
//			customerDTO.setCustomerFirstName(booking.getCustomer().getCustomerFirstName());
//			customerDTO.setContactNumber(booking.getCustomer().getContactNumber());
//			
//			RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
//			
//			roomsType.setRoomType(booking.getRoom().getRoomType());
//			roomsType.setRoomPrice(booking.getRoom().getRoomPrice());
//			
//			bookingDto.setCustomerDto(customerDTO);
//			bookingDto.setRoom(roomTypeDTO);
//			bookingDto.setFromDate(booking.getCheckInDate().toString());
//			bookingDto.setToDate(booking.getCHeckOutDate().toString());
//			bookingDto.setNoOfPeople(booking.getNoOfPeople());
//			bookingDto.setBookedOn(booking.getBookedOn().toString());
			
			map.put("Status", "Booked");
		}else {
			map.put("Error", "cutomer or room data not found");
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getAllBookings() {
		
		
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<RoomBooking> bookings = roomBookingRepository.findAll();	
				
		for (RoomBooking booking : bookings) {
			
			map.put("customerName", booking.getCustomer().getCustomerFirstName());
			map.put("customerLastName", booking.getCustomer().getCustomerLastName());
			map.put("customerContactNumber", booking.getCustomer().getContactNumber());
			map.put("roomType", booking.getRoom().getRoomType());
			map.put("roomPrice", booking.getRoom().getRoomPrice());
			map.put("checkInDate", booking.getCheckInDate().toString());
			map.put("checkOutdate", booking.getCHeckOutDate().toString());
			map.put("noOfPeople", booking.getNoOfPeople());
			map.put("roomBookedOn", booking.getBookedOn().toString());
			
			
		}
		
		return maps;
	}

	@Override
	public Object getbookingById(int id) {
		
		Map<String, Object> map = new HashMap<>();
		
		RoomBooking booking = roomBookingRepository.findById(id).orElseGet(null);
		if (booking!=null) {
			
			map.put("customerName", booking.getCustomer().getCustomerFirstName());
			map.put("customerLastName", booking.getCustomer().getCustomerLastName());
			map.put("customerContactNumber", booking.getCustomer().getContactNumber());
			map.put("roomType", booking.getRoom().getRoomType());
			map.put("roomPrice", booking.getRoom().getRoomPrice());
			map.put("checkInDate", booking.getCheckInDate().toString());
			map.put("checkOutdate", booking.getCHeckOutDate().toString());
			map.put("noOfPeople", booking.getNoOfPeople());
			map.put("roomBookedOn", booking.getBookedOn().toString());
			
		} else {
			map.put("Error", "Data not found on this ID");
		}
		return map;
	}

	@Override
	public Object updateBookIngById(int id, RoomBookingDTO bookingDTO) {
		
		Map<String, Object> map = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		RoomBooking booking = roomBookingRepository.findById(id).orElseGet(null);
		
		if (booking!=null) {
			
//			Customer customer = new Customer();
//			RoomsType roomsType = new RoomsType();
//			
//			booking.setCustomer(customer);
//			booking.setRoom(roomsType);
//			
//			booking.setCheckInDate(LocalDate.parse(bookingDTO.getFromDate(), formatter));
//			booking.setCHeckOutDate(LocalDate.parse(bookingDTO.getToDate(), formatter));
//			booking.setBookedOn(LocalDate.now());
// 			booking.setNoOfPeople(bookingDTO.getNoOfPeople());
//			
//			roomBookingRepository.save(booking);
//			
//			CustomerDTO customerDTO = new CustomerDTO();
//			
//			customerDTO.setCustomerFirstName(booking.getCustomer().getCustomerFirstName());
//			customerDTO.setContactNumber(booking.getCustomer().getContactNumber());
//			
//			RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
//			
//			roomsType.setRoomType(booking.getRoom().getRoomType());
//			roomsType.setRoomPrice(booking.getRoom().getRoomPrice());
//			roomsType.setRoomType(booking.getRoom().getRoomType());
//			
//			RoomBookingDTO bookingDto = new RoomBookingDTO();
//			bookingDto.setCustomerDto(customerDTO);
//			bookingDto.setRoom(roomTypeDTO);
//			bookingDto.setFromDate(booking.getCheckInDate().toString());
//			bookingDto.setToDate(booking.getCHeckOutDate().toString());
//			bookingDto.setNoOfPeople(booking.getNoOfPeople());
//			bookingDto.setBookedOn(booking.getBookedOn().toString());
			
			
			booking.setCustomer(booking.getCustomer());
			booking.setRoom(booking.getRoom());
			booking.setCheckInDate(LocalDate.parse(bookingDTO.getFromDate(), formatter));
			booking.setCHeckOutDate(LocalDate.parse(bookingDTO.getToDate(), formatter));
//			booking.setBookedOn(LocalDate.now());
 			booking.setNoOfPeople(bookingDTO.getNoOfPeople());
			
			roomBookingRepository.save(booking);
			
			map.put("bookingDetails", "Updated");
		} else {
			map.put("Error", "Id not found");
		}
		return map;
		
		
	}

	@Override
	public Object deleteBookingInfoById(int id) {

		Map<String, Object> map = new HashMap<>();
		
		RoomBooking booking = roomBookingRepository.findById(id).orElse(null);
		if (booking!=null) {
			roomBookingRepository.deleteById(id);
			
			map.put("booking", "Deleted");
		} else {
			map.put("Error", "Id not found");
		}
		return map;
	}
	
	
	
}
