package com.techpixe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techpixe.entity.Template;
import com.techpixe.service.TemplateService;

@RestController
@RequestMapping("/templates")
public class TemplateController {
	@Autowired
	private TemplateService templateService;

	@PostMapping("/create/{projectId}")
	public ResponseEntity<Template> createTemplateWithProject(@PathVariable Long projectId,
			@RequestBody Template template) {
		try {
			Template createdTemplate = templateService.createTemplateWithProject(template, projectId);
			return ResponseEntity.ok(createdTemplate);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/get/{templateId}")
	public ResponseEntity<String> getTemplateById(@PathVariable long templateId) {

		String getTemplate = templateService.getTemplateById(templateId);
		return ResponseEntity.ok(getTemplate);

	}

	@GetMapping("/{id}/download")
	public ResponseEntity<byte[]> downloadTemplate(@PathVariable long id) {
		return templateService.downloadTemplate(id);
	}
}
