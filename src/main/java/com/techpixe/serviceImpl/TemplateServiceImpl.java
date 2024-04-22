package com.techpixe.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpixe.entity.Project;
import com.techpixe.entity.Template;
import com.techpixe.entity.TextElement;
import com.techpixe.repository.ProjectRepository;
import com.techpixe.repository.TemplateRepository;
import com.techpixe.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	TemplateRepository templateRepository;

	public Template createTemplateWithProject(Template template, Long projectId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new IllegalArgumentException("Project not found"));

		List<TextElement> textElements = new ArrayList<>();
		for (TextElement textElement : template.getTextElements()) {
			textElement.setTemplate(template);
			textElement.setType("text");
			textElement.setName(textElement.getName());
			textElement.setText(textElement.getText());
			textElement.setTextSize(textElement.getTextSize());
			textElement.setFontStyle(textElement.getFontStyle());
			textElement.setTextColor(textElement.getTextColor());
			textElement.setAngle(textElement.getAngle());
			textElement.setDestX(textElement.getDestX());
			textElement.setDestY(textElement.getDestY());
			textElement.setMaxLength(textElement.getMaxLength());
			textElement.setMaxLines(textElement.getMaxLines());
			textElement.setLetterSpacing(textElement.getLetterSpacing());
			textElement.setInputLineHeight(textElement.getInputLineHeight());
			textElement.setTextAlign(textElement.getTextAlign());
			textElements.add(textElement);
		}
		// Set the list of text elements to the template
		template.setTextElements(textElements);
		template.setProject(project);

		// Save the template with its associated text elements
		Template savedTemplate = templateRepository.save(template);

		return savedTemplate;
	}
}
