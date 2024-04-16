package com.techpixe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techpixe.dto.ErrorResponseDto;
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
			@RequestParam String otp, @PathVariable String email, @PathVariable Long admin) {
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

	@PutMapping("/update/{userId}")
	public ResponseEntity<User> update(@PathVariable Long userId, @RequestParam(required = false) String fullName,
			@RequestParam(required = false) String email, @RequestParam(required = false) String password) {
		Optional<User> updatedUser = userService.updateUser(userId, fullName, email, password);
		return updatedUser.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	@PostMapping("/forgotPasswordForValidation/{email}")
	public ResponseEntity<String> forgotPasswordValidation(@PathVariable String email) {
		String message = userService.forgotPasswordForValidation(email);
		return ResponseEntity.ok(message);
	}

	@PostMapping("/forgotPassword/{email}")
	public ResponseEntity<?> forgotPassword(@PathVariable String email, @RequestParam String otp,
			@RequestParam String password) {
		if (email != null) {
			if (isEmail(email)) {
				return userService.forgotPassword(email, otp, password);
			} else {
				ErrorResponseDto errorResponseDto = new ErrorResponseDto();
				errorResponseDto.setError("****Invalid Email Pattern****");
				return ResponseEntity.internalServerError().body(errorResponseDto);
			}
		} else {
			ErrorResponseDto errorResponseDto = new ErrorResponseDto();
			errorResponseDto.setError("****Invalid Email Pattern****");
			return ResponseEntity.internalServerError().body(errorResponseDto);
		}
	}

	public boolean isEmail(String email) {
		return email.contains("@");
	}
}
