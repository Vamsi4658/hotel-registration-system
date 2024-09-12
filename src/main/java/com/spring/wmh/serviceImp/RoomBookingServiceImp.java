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
	public List<Object> getAllBookings() {
		
		List<Object> bookingObjects = new ArrayList<>();
		List<RoomBooking> bookings = roomBookingRepository.findAll();	
				
		for (RoomBooking booking : bookings) {
			
			CustomerDTO customerDTO = new CustomerDTO();
			RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
			RoomBookingDTO roomBookingDTO = new RoomBookingDTO();
					
			customerDTO.setCustomerFirstName(booking.getCustomer().getCustomerFirstName());
			customerDTO.setContactNumber(booking.getCustomer().getContactNumber());
				
			roomTypeDTO.setRoomType(booking.getRoom().getRoomType());
			roomTypeDTO.setRoomPrice(booking.getRoom().getRoomPrice());
			roomTypeDTO.setRoomType(booking.getRoom().getRoomType());

			roomBookingDTO.setCustomerDto(customerDTO);
			roomBookingDTO.setRoom(roomTypeDTO);
			roomBookingDTO.setFromDate(booking.getCheckInDate().toString());
			roomBookingDTO.setToDate(booking.getCHeckOutDate().toString());
			roomBookingDTO.setNoOfPeople(booking.getNoOfPeople());
			roomBookingDTO.setBookedOn(booking.getBookedOn().toString());
			
			bookingObjects.add(roomBookingDTO);
		}
		
		return bookingObjects;
	}

	@Override
	public Object getbookingById(int id) {
		
		Map<String, Object> map = new HashMap<>();
		
		RoomBooking booking = roomBookingRepository.findById(id).orElseGet(null);
		if (booking!=null) {
			
			CustomerDTO customerDTO = new CustomerDTO();
			RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
			RoomBookingDTO roomBookingDTO = new RoomBookingDTO();
					
			customerDTO.setCustomerFirstName(booking.getCustomer().getCustomerFirstName());
			customerDTO.setContactNumber(booking.getCustomer().getContactNumber());
				
			roomTypeDTO.setRoomType(booking.getRoom().getRoomType());
			roomTypeDTO.setRoomPrice(booking.getRoom().getRoomPrice());
			roomTypeDTO.setRoomType(booking.getRoom().getRoomType());

			roomBookingDTO.setCustomerDto(customerDTO);
			roomBookingDTO.setRoom(roomTypeDTO);
			roomBookingDTO.setFromDate(booking.getCheckInDate().toString());
			roomBookingDTO.setToDate(booking.getCHeckOutDate().toString());
			roomBookingDTO.setNoOfPeople(booking.getNoOfPeople());
			roomBookingDTO.setBookedOn(booking.getBookedOn().toString());
			
			map.put("Booking Details", roomBookingDTO);
		} else {
			map.put("data not found", null);
		}
		return map;
	}

	@Override
	public Object updateBookIngById(int id, RoomBookingDTO bookingDTO) {
		
		Map<String, Object> map = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		RoomBooking booking = roomBookingRepository.findById(id).orElseGet(null);
		
		if (booking!=null) {
			
			Customer customer = new Customer();
			RoomsType roomsType = new RoomsType();
			
			booking.setCustomer(customer);
			booking.setRoom(roomsType);
			
			booking.setCheckInDate(LocalDate.parse(bookingDTO.getFromDate(), formatter));
			booking.setCHeckOutDate(LocalDate.parse(bookingDTO.getToDate(), formatter));
			booking.setBookedOn(LocalDate.now());
 			booking.setNoOfPeople(bookingDTO.getNoOfPeople());
			
			roomBookingRepository.save(booking);
			
			CustomerDTO customerDTO = new CustomerDTO();
			
			customerDTO.setCustomerFirstName(booking.getCustomer().getCustomerFirstName());
			customerDTO.setContactNumber(booking.getCustomer().getContactNumber());
			
			RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
			
			roomsType.setRoomType(booking.getRoom().getRoomType());
			roomsType.setRoomPrice(booking.getRoom().getRoomPrice());
			roomsType.setRoomType(booking.getRoom().getRoomType());
			
			RoomBookingDTO bookingDto = new RoomBookingDTO();
			bookingDto.setCustomerDto(customerDTO);
			bookingDto.setRoom(roomTypeDTO);
			bookingDto.setFromDate(booking.getCheckInDate().toString());
			bookingDto.setToDate(booking.getCHeckOutDate().toString());
			bookingDto.setNoOfPeople(booking.getNoOfPeople());
			bookingDto.setBookedOn(booking.getBookedOn().toString());
			
			map.put("Booking Details", bookingDto);
		} else {
			map.put("data not found", null);
		}
		return map;
		
		
	}

	@Override
	public String deleteBookingInfoById(int id) {

		RoomBooking booking = roomBookingRepository.findById(id).orElse(null);
		if (booking!=null) {
			roomBookingRepository.deleteById(id);
			return "data deleted successfuly";
		} else {
			return "data NOT found";
		}
	}
	
	
	
}
