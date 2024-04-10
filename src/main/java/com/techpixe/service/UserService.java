package com.techpixe.service;

import com.techpixe.entity.User;

public interface UserService 
{
	User saveUser(String email);
	
	User registerUser(String fullName, String password, String otp, String email);
}
