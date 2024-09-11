package com.spring.wmh.serviceImp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.wmh.DTO.AdminDTO;
import com.spring.wmh.entity.Admin;
import com.spring.wmh.repository.AdminRepository;
import com.spring.wmh.service.AdminService;

@Service
public class AdminserviceImp implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public AdminDTO addAdmin(AdminDTO adminDTO) {
		
		Admin admin = new Admin();
		
		List<Admin> name = adminRepository.findByadminUserName(adminDTO.getAdminUserName());
		try {
			if (name.size()==0) {
				admin.setAdminUserName(adminDTO.getAdminUserName());
			} 
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		admin.setAdminUserName(adminDTO.getAdminUserName());
		admin.setAdminPassword(adminDTO.getAdminPassword());
		admin.setAdminCreatedOn(new Date().toString());
		adminRepository.save(admin);
		
		AdminDTO adminDto = new AdminDTO();
		adminDto.setAdminId(admin.getAdminId());
		adminDto.setAdminUserName(admin.getAdminUserName());
		adminDto.setAdminCreatedOn(admin.getAdminCreatedOn());

		return adminDto;
	}
}
