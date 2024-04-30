package com.techpixe.serviceImpl;

import java.util.ArrayList;
import com.techpixe.entity.Shapes;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

		List<Shapes> shapes = new ArrayList<>();
		for (Shapes shape : template.getShapes()) {
			shape.setTemplate(template);
			shape.setShapeName(shape.getShapeName());
			shape.setFillColour(shape.getFillColour());
			shape.setFillStyle(shape.getFillStyle());
			shape.setCustomHeight(shape.getCustomHeight());
			shape.setCustomWidth(shape.getCustomWidth());
			shape.setOpacity(shape.getOpacity());
			shape.setOutLineSize(shape.getOutLineSize());
			shape.setOutLineColour(shape.getOutLineColour());
			shape.setSkewX(shape.getSkewX());
			shape.setSkewY(shape.getSkewY());
			shape.setRotate(shape.getRotate());
			shapes.add(shape);
		}
		template.setShapes(shapes);
		template.setProject(project);
		// Save the template with its associated text elements
		Template savedTemplate = templateRepository.save(template);

		return savedTemplate;
	}

	@Override
	public String getTemplateById(long templateId) {
		Optional<Template> templateOptional = templateRepository.findById(templateId);
		if (templateOptional.isPresent()) {
			Template template = templateOptional.get();
			List<TextElement> textElements = template.getTextElements();
			List<Shapes> shapes = template.getShapes();

			StringBuilder htmlBuilder = new StringBuilder();

			// Append text elements
			if (!textElements.isEmpty()) {
				for (TextElement textElement : textElements) {
					htmlBuilder.append("<div style=\"");
					htmlBuilder.append("font-style: ").append(textElement.getFontStyle()).append("; ");
					htmlBuilder.append("font-size: ").append(textElement.getTextSize()).append("px; ");
					htmlBuilder.append("color: rgb(").append(textElement.getTextColor()).append(");");
					htmlBuilder.append("transform: rotate(").append(textElement.getAngle()).append("deg);");
					htmlBuilder.append("position: absolute; left: ").append(textElement.getDestX()).append("%; top: ")
							.append(textElement.getDestY()).append("%;");
					htmlBuilder.append("max-width: ").append(textElement.getMaxLength()).append("%;");
					htmlBuilder.append("line-height: ").append(textElement.getInputLineHeight()).append(";");
					htmlBuilder.append("letter-spacing: ").append(textElement.getLetterSpacing()).append("px;");
					htmlBuilder.append("text-align: ").append(textElement.getTextAlign()).append(";\">");
					htmlBuilder.append(textElement.getText());
					htmlBuilder.append("</div>");
				}
			} else {
				htmlBuilder.append("No text elements found for the template.");
			}

			List<Shapes> shapesList = template.getShapes();
			if (!shapesList.isEmpty()) {

				for (Shapes shape : shapesList) {
					htmlBuilder.append("<div style=\"");
					// Adjust the following attributes according to your Shapes entity
					htmlBuilder.append("width: ").append(shape.getCustomWidth()).append("px; ");
					htmlBuilder.append("height: ").append(shape.getCustomHeight()).append("px; ");
					htmlBuilder.append("background-color: ").append(shape.getFillColour()).append("; ");
					htmlBuilder.append("border: ").append(shape.getOutLineSize()).append("px solid ")
							.append(shape.getOutLineColour()).append("; ");
					htmlBuilder.append("border-radius: ").append(shape.getCustomWidth() / 2).append("px; "); // Assuming
																												// circular
																												// shapes
					htmlBuilder.append("opacity: ").append(shape.getOpacity()).append("; ");
					htmlBuilder.append("skewX(").append(shape.getSkewX()).append("deg) "); // Skew X
					htmlBuilder.append("skewY(").append(shape.getSkewY()).append("deg); "); // Skew Y
					// Additional shape-specific attributes can be appended here
					htmlBuilder.append("\"></div>");
				}
				return htmlBuilder.toString();
			}
			return "No shapes found for the template.";
		}
		return "Template not found.";
	}

	@Override
	public ResponseEntity<byte[]> downloadTemplate(long id) {
		Optional<Template> templateOptional = templateRepository.findById(id);
		if (templateOptional.isPresent()) {
			Template template = templateOptional.get();
			// String htmlContent = generateHtmlContent(template, id);

			String htmlcontent = getTemplateById(id);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.TEXT_HTML);
			headers.setContentDispositionFormData("attachment", "template.html");

			return new ResponseEntity<>(htmlcontent.getBytes(), headers, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

}
