package com.techpixe.service;

import java.util.List;

import com.techpixe.entity.Project;

public interface ProjectService {
	Project createProject(String projectName, Long user);

	Project getProjectById(Long id);

	List<Project> allProjects();

	void deleteByProjectId(Long id);

}
