package com.techpixe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Shapes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shapeId;

	private String shapeName;

	private String fillStyle;

	private String fillColour;

	private Long outLineSize;

	private String outLineColour;

	private boolean shadowEffect;

	private Double opacity;

	private Long skewX;

	private Long skewY;

	private String rotate;

	private Long customWidth;

	private Long customHeight;

	// private Long aspectRatio;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "templateId")
	private Template template;

}
