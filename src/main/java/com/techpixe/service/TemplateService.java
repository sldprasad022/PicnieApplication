package com.techpixe.service;


import com.techpixe.entity.Template;

public interface TemplateService {

	Template createTemplateWithProject(Template template, Long projectId);

}
