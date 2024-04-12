package com.techpixe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
	private Long AdminId;

	private String fullName;

	private String email;

	private Long MobileNumber;

	private String password;

	private String role;
}
