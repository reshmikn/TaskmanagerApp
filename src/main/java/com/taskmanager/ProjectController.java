package com.taskmanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class ProjectController {

	

	@Autowired
	ProjectService projectService;
	
	@PostMapping(value = "/addProject")
	public Project addProject(@RequestBody Project project) {
		return projectService.addProject(project);
		
 
	}
	
	 @GetMapping("/viewProject")
	    public List<Project> getAllProjects() {
	        return projectService.viewProject();
	    }
	 
	 @GetMapping("/editProject/{id}")
	    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") int projectId)
	        throws ResourceNotFoundException {
		 
	        Project project = (Project) projectService.findById(projectId);
		 
	        return ResponseEntity.ok().body(project);
	    }
	 
	 @PutMapping("/updateProject")
	    public ResponseEntity<Project> updateProject(@RequestBody Project projectDetails) throws ResourceNotFoundException {
	        Project project = (Project) projectService.findById(projectDetails.getProjectId());
	        project.setProjectId(projectDetails.getProjectId());;
	        project.setProjectName(projectDetails.getProjectName());
	        project.setStartDate(projectDetails.getStartDate());
	        project.setEndDate(projectDetails.getEndDate());
	        project.setUserId(projectDetails.getUserId());
	        project.setPriority(projectDetails.getPriority());
		    final Project updatedProject = projectService.save(project);
	        return ResponseEntity.ok(updatedProject);
	        }
	 
	 
	  @DeleteMapping("/deleteProject/{id}")
	    public Map<String, Boolean> deleteProject(@PathVariable(value = "id") int projectId)
	         throws ResourceNotFoundException {
		    Project project = (Project) projectService.findById(projectId);
	       

		    projectService.delete(project);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
}
