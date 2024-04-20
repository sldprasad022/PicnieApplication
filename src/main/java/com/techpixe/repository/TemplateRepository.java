package com.techpixe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techpixe.entity.Template;

public interface TemplateRepository extends JpaRepository<Template, Long> {

}
