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
		
		if (roomBooking!=null) {
			Payment payment = new Payment();

			payment.setPaymentMode(paymentDTO.getPaymentMode());
			payment.setPaymentDate(LocalDate.now());
			payment.setPaymentStatus(paymentDTO.getPaymentStatus());
			payment.setCardNumber(paymentDTO.getCardNumber());
			payment.setCvv(paymentDTO.getCvv());

			map.put("status", " Payment successfully ");

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

			payment.setCvv(paymentDTO.getCvv());

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
