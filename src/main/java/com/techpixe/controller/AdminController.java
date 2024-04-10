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

import com.techpixe.entity.Admin;
import com.techpixe.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/save")
	public ResponseEntity<Admin> save(@RequestParam String fullName, @RequestParam String email, @RequestParam Long MobileNumber, @RequestParam String password)
	{
		Admin saved = adminService.save(fullName, email, MobileNumber, password);
		return new ResponseEntity<Admin>(saved, HttpStatus.CREATED);
	}
	
	@GetMapping("/getByAdminId/{adminId}")
	public ResponseEntity<?> getByAdminId(@PathVariable("adminId") Long id)
	{
		Admin fetchedAdmin = adminService.getByAdminId(id);
		return ResponseEntity.ok(fetchedAdmin);
	}
}
