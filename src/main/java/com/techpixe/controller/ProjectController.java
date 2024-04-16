package com.techpixe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techpixe.entity.Project;
import com.techpixe.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@PostMapping("/saveProject/{user}")
	public ResponseEntity<Project> createProject(@RequestParam String projectName, @RequestParam Boolean status,
			@PathVariable Long user) {
		Project savedProject = projectService.createProject(projectName, status, user);
		return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
	}

	@GetMapping("/getProjectById/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable("projectId") Long id) {
		Project fetchedProjectById = projectService.getProjectById(id);
		return ResponseEntity.ok(fetchedProjectById);
	}

	@GetMapping("/getAllProjects")
	public ResponseEntity<List<Project>> all() {
		List<Project> allProjects = projectService.allProjects();
		return ResponseEntity.ok(allProjects);
	}

	@DeleteMapping("/deleteProjectById/{projectId}")
	public ResponseEntity<Void> deleteProjectById(@PathVariable("projectId") Long id) {
		Project delete = projectService.getProjectById(id);
		if (delete == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			System.out.println("****Project is deleted*****");

			projectService.deleteByProjectId(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
