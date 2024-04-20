package com.techpixe.serviceImpl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.techpixe.entity.Project;
import com.techpixe.entity.Template;
import com.techpixe.repository.ProjectRepository;
import com.techpixe.repository.TemplateRepository;
import com.techpixe.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TemplateRepository templateRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Template createTemplate(String templateName, Long project) {
		Project project1 = projectRepository.findById(project)
				.orElseThrow(() -> new NoSuchElementException("Project is not Present"));
		if (project1 != null) {
			Template template = new Template();
			template.setTemplateName(templateName);
			template.setType("Image");
			template.setProject(project1);

			return templateRepository.save(template);
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Project Id is not Present" + project);
		}

	}

}
