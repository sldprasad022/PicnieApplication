package com.techpixe.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TextElement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id")
	@JsonIgnore
	private Template template;

	private String name;
	private String type;
	private String text;
	private int textSize;
	private String fontStyle;
	private String textColor;
	private double angle;
	private double destX;
	private double destY;
	private double maxLength;
	private int maxLines;
	private double letterSpacing;
	private double inputLineHeight;
	private String textAlign;

}
