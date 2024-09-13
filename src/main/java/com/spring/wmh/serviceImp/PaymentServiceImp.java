package com.spring.wmh.serviceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.wmh.DTO.PaymentDTO;
import com.spring.wmh.entity.Payment;
import com.spring.wmh.entity.RoomBooking;
import com.spring.wmh.repository.PaymentRepository;
import com.spring.wmh.repository.RoomBookingRepository;
import com.spring.wmh.service.PaymentService;

@Service
public class PaymentServiceImp implements PaymentService{
	
	 @Autowired
	 private PaymentRepository paymentRepository;
	 @Autowired
	 private RoomBookingRepository bookingRepository;

	@Override
	public Object savePayment(int id, PaymentDTO paymentDTO) {
		
		Map<String, Object> map = new HashMap<>();
		RoomBooking roomBooking = bookingRepository.findById(id).orElse(null);
		
		Payment pD = paymentRepository.findByCardNumber(paymentDTO.getCardNumber()).orElse(null);
		
		if (roomBooking!=null) {
			
			Payment payment = new Payment();
			
			payment.setRoomBooking(roomBooking);
			/*
			 *  Validating the info
			 */
			if (paymentDTO.getPaymentMode()!=null) {				
				if (paymentDTO.getPaymentMode().equals("") || paymentDTO.getPaymentMode().equals(" ")) {					
					map.put("paymentMode", "should not be empty");
				} else {
					payment.setPaymentMode(paymentDTO.getPaymentMode());
				}
			} else {
				map.put("paymentMode", "should not be null");
			}
			if (paymentDTO.getCardNumber()==null) {				
				map.put("cardNumber", "Card Number should not null"); // error message
			} else {
				
				if (paymentDTO.getCardNumber().length()==16)					
					if (pD==null) {
						payment.setCardNumber(paymentDTO.getCardNumber());
					} else {						
						map.put("cardNumber", "card number is already existed");
					}
				else 
					map.put("cardNumber", "Card Number should be 16 digits");
			}
			if (paymentDTO.getCvv()==null) {											
				map.put("cvv", " should not be null");				
			} else {
				
				if (paymentDTO.getCvv().length()==3)	
					payment.setCvv(Integer.valueOf(paymentDTO.getCvv()));
				else 				
					map.put("cvv", " should be 3 Digits");	
			}
			payment.setPaymentStatus(paymentDTO.getPaymentStatus());
			payment.setPaymentDate(LocalDate.now());
			/*
			 * 
			 *     push payload data into DB
			 * 
			 */
			if (map.size()==0) {				
				paymentRepository.save(payment);
				map.put("status", " Payment successfully ");
				return map;
			}		

		} else {
			
			map.put("error", "Booking id not found");
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getAllPayment() {
		
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		List<Payment> payments = paymentRepository.findAll();

		Map<String, Object> map = null;
		
		for( Payment payment : payments) {

			map = new HashMap<String, Object>();

		    map.put("paymentMode", payment.getPaymentMode());
			map.put("paymentDate", payment.getPaymentDate());
			map.put("paymentStatus", payment.getPaymentStatus());
			map.put("cardNumber",payment.getCardNumber());
//			map.put("cvv", payment.getCvv());
			map.put("bookingId", payment.getRoomBooking().getBookingId());
			
			maps.add(map);
		}
		
		    return maps;
	}

	@Override
	public Object getPaymentById(int id) {
		
		Map<String, Object> map =new HashMap<>();

		Payment payment = paymentRepository.findById(id).orElse(null);

		if(payment != null) {

			map.put("paymentMode", payment.getPaymentMode());
			map.put("paymentDate", payment.getPaymentDate());
			map.put("paymentStatus", payment.getPaymentStatus());
			map.put("cardNumber", payment.getCardNumber());
//			map.put("cvv", payment.getCvv());
			map.put("bookingId", payment.getRoomBooking().getBookingId());

		}else {
			map.put("Error", "Payment details not found");
		}
		return map;
	}

	@Override
	public Object updatePaymentById(int id, PaymentDTO paymentDTO) {
		Map<String, Object> map = new HashMap<>();		

		PaymentDTO paymentDTO2 = new PaymentDTO();
		Payment payment = paymentRepository.findById(id).orElse(null);

		if(payment!=null) {

			payment.setPaymentMode(paymentDTO.getPaymentMode());

			payment.setPaymentDate(payment.getPaymentDate());

			payment.setPaymentStatus(paymentDTO.getPaymentStatus());

			payment.setCardNumber(paymentDTO.getCardNumber());

			payment.setCvv(Integer.valueOf(paymentDTO.getCvv()));

			paymentRepository.save(payment);

			map.put("Status", "Sucessfully Updated");

		}else {

			map.put("Error", "payment id is not found");

		}
		return map;
		
	}

//	@Override
//	public Object removePaymentById(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
