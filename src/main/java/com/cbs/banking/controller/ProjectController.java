package com.cbs.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.banking.model.Project;
import com.cbs.banking.repository.ProjectRepository;

@RestController
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@PostMapping("/project")
	public Project postProject(@RequestBody Project project) {
		return projectRepository.save(project);
	}
	
	@GetMapping("/project")
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}
}
