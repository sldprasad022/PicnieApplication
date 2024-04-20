package com.techpixe.service;

import com.techpixe.entity.Template;

public interface TemplateService {
	Template createTemplate(String templateName, Long project);
}
