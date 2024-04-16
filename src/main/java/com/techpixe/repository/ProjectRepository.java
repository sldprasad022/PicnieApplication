package com.techpixe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techpixe.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
