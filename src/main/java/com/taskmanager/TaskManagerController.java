package com.taskmanager;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin("http://localhost:4200")
public class TaskManagerController {
	
	@Autowired
	TaskManagerService taskManagerService;
	@RequestMapping("/hello")  
    public String hello(){  
        return"Hello!";  
    }  
	
	@PostMapping(value = "/addTask")
	public Task addTask(@RequestBody Task task) {
		return taskManagerService.addTask(task);
		
 
	}
	
	 @GetMapping("/viewTask/{id}")
	    public List<Task> getAllTasks(@PathVariable(value = "id") int projectId) {
	        return taskManagerService.viewTask(projectId);
	    }
	 
	/*
	 * @GetMapping("/editTask") public List<Task> getAllTasks() { return
	 * taskManagerService.viewTask(); }
	 */
	 
	 @GetMapping("/editTask/{id}")
	    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") int taskId)
	        throws ResourceNotFoundException {
		 
	        Task task = (Task) taskManagerService.findById(taskId);
		 
	        return ResponseEntity.ok().body(task);
	    }
	 
	 @PutMapping("/updateTask")
	    public ResponseEntity<Task> updateTask(@RequestBody Task TaskDetails) throws ResourceNotFoundException {
	        Task Task = (com.taskmanager.Task) taskManagerService.findById(TaskDetails.getTaskId());
	        
	        Task.setTask(TaskDetails.getTask());
	        Task.setPriority(TaskDetails.getPriority());
		    Task.setStartDate(TaskDetails.getStartDate());
		    Task.setEndDate(TaskDetails.getEndDate());
		    Task.setParentTask(TaskDetails.getParentTask());
		    Task.getpTask().setParentTask(TaskDetails.getParentTask());
		    final Task updatedTask = taskManagerService.save(Task);
	        return ResponseEntity.ok(updatedTask);
	        }
	 
	  @DeleteMapping("/endTask/{id}")
	    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") int taskId)
	         throws ResourceNotFoundException {
		    Task task = (Task) taskManagerService.findById(taskId);
	       

		    taskManagerService.delete(task);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }

}