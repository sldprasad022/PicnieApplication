package com.techpixe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techpixe.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
