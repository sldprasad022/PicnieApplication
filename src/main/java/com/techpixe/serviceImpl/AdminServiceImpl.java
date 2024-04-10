package com.techpixe.serviceImpl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpixe.entity.Admin;
import com.techpixe.repository.AdminRepository;
import com.techpixe.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService
{
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin save(String fullName, String email, Long MobileNumber, String password)
	{
		Admin admin = new Admin();
		admin.setFullName(fullName);
		admin.setEmail(email);
		admin.setMobileNumber(MobileNumber);
		admin.setPassword(password);
		admin.setRole("Admin");
		return adminRepository.save(admin);
	}

	@Override
	public Admin getByAdminId(Long id) 
	{
		return adminRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Admin Id "+id+" is not present"));
	}

	
}
