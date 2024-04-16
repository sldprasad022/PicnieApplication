package com.techpixe.service;

import org.springframework.http.ResponseEntity;

import com.techpixe.entity.Admin;

public interface AdminService {
	Admin save(String fullName, String email, Long MobileNumber, String password);

	Admin getByAdminId(Long id);

	ResponseEntity<?> loginByEmail(String email, String password);

	ResponseEntity<?> changePassword(Long id, String password, String newPassword);

	ResponseEntity<?> forgotPassword(String email, String newPassword);
}
