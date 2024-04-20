package com.techpixe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techpixe.entity.Template;
import com.techpixe.service.TemplateService;

@RestController
@RequestMapping("/template")
public class TemplateController {
	@Autowired
	private TemplateService templateService;

	@PostMapping("/createTemplate/{project}")
	private ResponseEntity<Template> createTemplate(@RequestParam String templateName, @PathVariable Long project) {
		Template savedTemplate = templateService.createTemplate(templateName, project);
		return new ResponseEntity<Template>(savedTemplate, HttpStatus.CREATED);
	}
}
