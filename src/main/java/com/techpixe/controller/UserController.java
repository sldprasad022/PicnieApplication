package com.techpixe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techpixe.entity.User;
import com.techpixe.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestParam String email)
	{
		User saved = userService.saveUser(email);
		return ResponseEntity.ok(saved);
	}
	
	@PostMapping("/register/{email}")
	public ResponseEntity<User> registerUser(@RequestParam String fullName, @RequestParam String password, @RequestParam String otp, @PathVariable String email)
	{
		User registeredUser = userService.registerUser(fullName, password, otp, email);
		return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
	}
}
