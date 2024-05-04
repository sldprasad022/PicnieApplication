package com.techpixe.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.techpixe.entity.Project;
import com.techpixe.entity.User;
import com.techpixe.repository.ProjectRepository;
import com.techpixe.repository.UserRepository;
import com.techpixe.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Project createProject(String projectName, Long user) {
		User user1 = userRepository.findById(user)
				.orElseThrow(() -> new NoSuchElementException("User with this Id is not Present " + user));
		if (user1 != null) {
			Project project = new Project();
			project.setProjectName(projectName);
			project.setStatus(true);
			project.setUser(user1);
			project.setCreated(LocalDateTime.now());

			return projectRepository.save(project);
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"User with this Id is not Present \"+user");
		}
	}

	@Override
	public Project getProjectById(Long id) {
		return projectRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Project with this Id is not Present  " + id));
	}

	@Override
	public List<Project> allProjects() {
		List<Project> all = projectRepository.findAll();
		if (all.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "****No Projects are present****");
		}
		return all;
	}

	@Override
	public void deleteByProjectId(Long id) {
		projectRepository.deleteById(id);
	}

}
