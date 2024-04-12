package com.techpixe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techpixe.entity.User;
import com.techpixe.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestParam String email) {
		User saved = userService.saveUser(email);
		return ResponseEntity.ok(saved);
	}

	@PostMapping("/register/{email}/{admin}")
	public ResponseEntity<User> registerUser(@RequestParam String fullName, @RequestParam String password,
			@RequestParam String otp, @PathVariable String email, @PathVariable(required = false) Long admin) {
		User registeredUser = userService.registerUser(fullName, password, otp, email, admin);
		return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
	}

	@PostMapping("/resendOTP/{email}")
	public ResponseEntity<String> resendOTP(@PathVariable String email) {
		String result = userService.resendOTP(email);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/getById/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable Long userId) {
		User getById = userService.getUserById(userId);
		return ResponseEntity.ok(getById);
	}
}
