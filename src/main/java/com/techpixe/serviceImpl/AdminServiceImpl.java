package com.techpixe.serviceImpl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.techpixe.dto.AdminDto;
import com.techpixe.dto.ErrorResponseDto;
import com.techpixe.dto.UserDto;
import com.techpixe.entity.Admin;
import com.techpixe.entity.User;
import com.techpixe.repository.AdminRepository;
import com.techpixe.repository.UserRepository;
import com.techpixe.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Admin save(String fullName, String email, Long MobileNumber, String password) {
		Admin admin = new Admin();
		admin.setFullName(fullName);
		admin.setEmail(email);
		admin.setMobileNumber(MobileNumber);
		admin.setPassword(password);
		admin.setRole("Admin");
		return adminRepository.save(admin);
	}

	@Override
	public Admin getByAdminId(Long id) {
		return adminRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Admin Id " + id + " is not present"));
	}

	@Override
	public ResponseEntity<?> loginByEmail(String email, String password) {
		Admin admin1 = adminRepository.findByEmail(email);
		User user1 = userRepository.findByEmail(email);
		if (admin1 != null && admin1.getPassword().equals(password)) {
			System.out.println("****Admin Login is Succesfull****");

			AdminDto adminDto = new AdminDto();
			adminDto.setAdminId(admin1.getAdminId());
			adminDto.setFullName(admin1.getFullName());
			adminDto.setEmail(admin1.getEmail());
			adminDto.setMobileNumber(admin1.getMobileNumber());
			adminDto.setPassword(admin1.getPassword());
			adminDto.setRole(admin1.getRole());
			return ResponseEntity.ok(adminDto);
		} else if (user1 != null && user1.getPassword().equals(password)) {
			System.out.println("****User Login is Succesfull****");

			UserDto userDto = new UserDto();
			userDto.setUserId(user1.getUserId());
			userDto.setFullName(user1.getFullName());
			userDto.setEmail(user1.getEmail());
			userDto.setPassword(user1.getPassword());
			userDto.setOtp(user1.getOtp());
			userDto.setRole(user1.getRole());
			return ResponseEntity.ok(userDto);
		} else {
			ErrorResponseDto errorResponseDto = new ErrorResponseDto();
			errorResponseDto.setError("Invalid Email or Password");
			return ResponseEntity.internalServerError().body(errorResponseDto);
		}
	}

	@Override
	public ResponseEntity<?> changePassword(Long id, String password, String newPassword) {
		Admin admin = adminRepository.findById(id).orElse(null);
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("****Id is not present****"));
		if (admin != null && admin.getPassword().equals(password)) {
			AdminDto adminDto = new AdminDto();
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setFullName(admin.getFullName());
			adminDto.setEmail(admin.getEmail());
			adminDto.setMobileNumber(admin.getMobileNumber());
			adminDto.setRole(admin.getRole());
			adminDto.setPassword(newPassword);

			admin.setPassword(newPassword);
			adminRepository.save(admin);

			return ResponseEntity.ok(adminDto);
		} else if (user != null && user.getPassword().equals(password)) {
			UserDto userDto = new UserDto();
			userDto.setUserId(user.getUserId());
			userDto.setFullName(user.getFullName());
			userDto.setEmail(user.getEmail());
			userDto.setOtp(user.getOtp());
			userDto.setRole(user.getRole());
			userDto.setPassword(newPassword);

			user.setPassword(newPassword);
			userRepository.save(user);

			return ResponseEntity.ok(userDto);
		} else {
			ErrorResponseDto errorResponseDto = new ErrorResponseDto();
			errorResponseDto.setError("****Old Password is not Matching****");
			return ResponseEntity.internalServerError().body(errorResponseDto);
		}
	}

	@Override
	public ResponseEntity<?> forgotPassword(String email, String newPassword) {
		Admin admin1 = adminRepository.findByEmail(email);
		if (admin1 != null) {
			admin1.setPassword(newPassword);
			adminRepository.save(admin1);

			AdminDto adminDto = new AdminDto();
			adminDto.setAdminId(admin1.getAdminId());
			adminDto.setEmail(admin1.getEmail());
			adminDto.setFullName(admin1.getFullName());
			adminDto.setMobileNumber(admin1.getMobileNumber());
			adminDto.setRole(admin1.getRole());

			adminDto.setPassword(newPassword);

			return ResponseEntity.ok(adminDto);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email Id is not Present " + email);
		}
	}

}
