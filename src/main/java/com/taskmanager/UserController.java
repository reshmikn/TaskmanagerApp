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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

		
		@Autowired
		UserService userService;
	
		
		@PostMapping(value = "/addUser")
		public User addTask(@RequestBody User user) {
			return userService.addUser(user);
			
	 
		}
		

		 @GetMapping("/viewUser")
		    public List<User> getAllUsers() {
		        return userService.viewUser();
		    }
		 
		 @GetMapping("/editUser/{id}")
		    public ResponseEntity<User> getTaskById(@PathVariable(value = "id") int userId)
		        throws ResourceNotFoundException {
			 
		        User user = (User) userService.findById(userId);
			 
		        return ResponseEntity.ok().body(user);
		    }
		 
		 
		 @PutMapping("/updateUser")
		    public ResponseEntity<User> updateUser(@RequestBody User userDetails) throws ResourceNotFoundException {
		        User user = (User) userService.findById(userDetails.getUserId());
		        user.setUserId(userDetails.getUserId());
		        user.setEmployeeId(userDetails.getEmployeeId());
		        user.setFirstName(userDetails.getFirstName());
		        user.setLastName(userDetails.getLastName());
		      
			    final User updatedUser = userService.save(user);
		        return ResponseEntity.ok(updatedUser);
		        }
		 
		  @DeleteMapping("/deleteUser/{id}")
		    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId)
		         throws ResourceNotFoundException {
			    User user = (User) userService.findById(userId);
		       

			    userService.delete(user);
		        Map<String, Boolean> response = new HashMap<>();
		        response.put("deleted", Boolean.TRUE);
		        return response;
		    }
		 
}
