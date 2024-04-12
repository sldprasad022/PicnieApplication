package com.techpixe.service;

import org.springframework.http.ResponseEntity;

import com.techpixe.entity.User;

public interface UserService {
	User saveUser(String email);

	User registerUser(String fullName, String password, String otp, String email, Long admin);

	// resend otp
	String resendOTP(String email);

	User getUserById(Long userId);

}
