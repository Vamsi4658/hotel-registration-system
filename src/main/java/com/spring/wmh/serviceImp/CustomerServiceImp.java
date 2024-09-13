package com.spring.wmh.serviceImp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.wmh.DTO.AdminDTO;
import com.spring.wmh.DTO.CustomerDTO;
import com.spring.wmh.DTO.SearchExistingCustomerDto;
import com.spring.wmh.entity.Admin;
import com.spring.wmh.entity.Customer;
import com.spring.wmh.repository.AdminRepository;
import com.spring.wmh.repository.CustomeRepository;
import com.spring.wmh.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService{
	
	@Autowired
	private CustomeRepository customeRepository;
	@Autowired
	private AdminRepository adminRepository;


	@Override
	public Object saveCustomer(int id, CustomerDTO customerDTO) { 
		
		Map<String, Object> map =new HashMap<>();

		Admin admin = adminRepository.findById(id).orElse(null);
		if (admin !=null) {
			
			Customer customer = new Customer();
			
			customer.setCustomerFirstName(customerDTO.getCustomerFirstName());
			customer.setCustomerLastName(customerDTO.getCustomerLastName());
			customer.setCustomerEmail(customerDTO.getCustomerEmail());
			customer.setContactNumber(customerDTO.getContactNumber());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			customer.setDob(LocalDate.parse(customerDTO.getDob(),formatter));
			
			customer.setAddress1(customerDTO.getAddress1());
			customer.setAddress2(customerDTO.getAddress2());
			customer.setCity(customerDTO.getCity());
			customer.setPincode(customerDTO.getPincode());
			customer.setCustomerCreatedOn(LocalDate.now());
			
			customer.setAdmin(admin);
			
			customeRepository.save(customer);
			
			map.put("added sucessfull", "customer: "+customer.getCustomerFirstName()+" "+customer.getCustomerLastName());
			
		} else {
			map.put("error", "Admin id not found");
		}
		
		return map;
	}


	@Override
	public List<Map<String, Object>> getAllCustomers() {
		
		List<Map<String, Object>> customerObjs = new ArrayList<Map<String, Object>>();
		
		List<Customer> all = customeRepository.findAll();
		Map<String, Object> map = null;
//	
		for (Customer customer : all) {
			
			map= new HashMap<String, Object>();
			
			map.put("customerName", customer.getCustomerFirstName()+" "+customer.getCustomerLastName());
			map.put("customerEmailId", customer.getCustomerEmail());
			map.put("customerContactNumber", customer.getContactNumber());
			map.put("address1", customer.getAddress1());
			map.put("address2", customer.getAddress2());
			map.put("city", customer.getCity());
			map.put("pincode", customer.getPincode());
			map.put("adminId", customer.getAdmin().getAdminId());
			map.put("adminName", customer.getAdmin().getAdminUserName());
			
			customerObjs.add(map);
		}
		return customerObjs;
	}


	@Override
	public Object getCustomerById(int id) {
		
		Map<String, Object> map = new HashMap<>();
	
		Customer customer = customeRepository.findById(id).orElse(null);
		
		if (customer!=null) {
			
			Map<String, Object> map2 = new HashMap<>();	
			
			map2.put("customerName", customer.getCustomerFirstName()+" "+customer.getCustomerLastName());
			map2.put("customerEmailId", customer.getCustomerEmail());
			map2.put("customerContactNumber", customer.getContactNumber());
			map2.put("address1", customer.getAddress1());
			map2.put("address2", customer.getAddress2());
			map2.put("city", customer.getCity());
			map2.put("incode", customer.getPincode());
			map2.put("adminId", customer.getAdmin().getAdminId());
			map2.put("adminName", customer.getAdmin().getAdminUserName());
			   
			map.put("Customer details: ", map2);;
			
		} else {
			map.put("Error", "Customer not found");
		}
		return map;
	}


	@Override
	public Object updateCustomerById(int id, CustomerDTO customerDTO) {
		
		Map<String, Object> map = new HashMap<>();
		CustomerDTO customerDto = new CustomerDTO();
		Customer customer = customeRepository.findById(id).orElse(null);
		
		if (customer!=null) {
		
			customer.setCustomerFirstName(customerDTO.getCustomerFirstName());
			customer.setCustomerLastName(customerDTO.getCustomerLastName());
			customer.setCustomerEmail(customerDTO.getCustomerEmail());
			customer.setContactNumber(customerDTO.getContactNumber());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			customer.setDob(LocalDate.parse(customerDTO.getDob(),formatter));
			
			customer.setAddress1(customerDTO.getAddress1());
			customer.setAddress2(customerDTO.getAddress2());
			customer.setCity(customerDTO.getCity());
			customer.setPincode(customerDTO.getPincode());
			customeRepository.save(customer);
			
					
			map.put("Status", "sucessfully updated" );
			
		} else {
			map.put("Error", " customer id is not found");
		}
		return map;
	}


	@Override
	public Object removeCustomerById(int id) {
		
		Map<String, Object> map = new HashMap<>();
		
		Customer customer = customeRepository.findById(id).orElse(null);
		
		if (customer!=null) {
			
			customeRepository.deleteById(id);
			map.put("Status", "Data removed Sucessfully...");
			
		} else
			map.put("Error", "customer not found");
		
		return map;
	}


	@Override
	public List<Map<String, Object>> searchExistedCustomer(SearchExistingCustomerDto existingCustomerDto) {
		
		String customerContact = existingCustomerDto.getCustomerContact();
		String customerLastName = existingCustomerDto.getCustomerLastName();
				
		// Query the database for matching customers
	    List<Customer> customers = customeRepository.findByLastNameOrContact(customerLastName, customerContact);
	    List<Map<String, Object>> maps = new ArrayList<>();
	    Map<String, Object> map = new HashMap<>();

	    if (customers.size()!=0) {
	    	for (Customer customer : customers) {
	    		
		        // Populate the map with customer details
		        map.put("customerName", customer.getCustomerFirstName() + " " + customer.getCustomerLastName());
		        map.put("customerEmailId", customer.getCustomerEmail());
		        map.put("customerContactNumber", customer.getContactNumber());
		        map.put("address1", customer.getAddress1());
		        map.put("address2", customer.getAddress2());
		        map.put("city", customer.getCity());
		        map.put("pincode", customer.getPincode());
		        map.put("adminId", customer.getAdmin().getAdminId());
		        map.put("adminName", customer.getAdmin().getAdminUserName());
		        

		        // Add map to the list
		        maps.add(map);
		    }
		} else {
			map.put("Error", "Data not found");
		}
	    return maps;
	}
	
	
	

}
