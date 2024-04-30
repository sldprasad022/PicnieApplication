package com.techpixe.service;

import org.springframework.http.ResponseEntity;

import com.techpixe.entity.Template;

public interface TemplateService {

	Template createTemplateWithProject(Template template, Long projectId);

	String getTemplateById(long templateId);

	ResponseEntity<byte[]> downloadTemplate(long id);

}
