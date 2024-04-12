package com.techpixe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Long userId;

	private String fullName;

	private String email;

	private String password;

	private String otp;

	private String role;
}
