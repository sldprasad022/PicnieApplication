package com.techpixe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Template {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long templateId;

	private String templateName;

	private String type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "projectId")
	private Project project;
}
