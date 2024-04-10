package com.techpixe.service;

import com.techpixe.entity.Admin;

public interface AdminService {
	Admin save(String fullName, String email, Long MobileNumber, String password);

	Admin getByAdminId(Long id);
}
