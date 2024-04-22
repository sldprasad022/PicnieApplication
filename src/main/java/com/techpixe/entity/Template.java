package com.techpixe.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Template {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "template_name")
	private String templateName;

	@Column(name = "type")
	private String type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "projectId")
	private Project project;

	@OneToMany(mappedBy = "template", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<TextElement> textElements;

}
