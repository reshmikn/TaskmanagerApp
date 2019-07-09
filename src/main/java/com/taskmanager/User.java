package com.taskmanager;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


	@Entity
	@Table(name = "user")

	public class User {
		@Id
		 @GeneratedValue(strategy = GenerationType.AUTO)
		 @Column(name="userId")
		 private int userId;
		
		 @Column(name="firstName")
		 private String firstName;
		 @Column(name="lastName")
		 private String lastName;
		 @Column(name="employeeId")
		 private String employeeId;
		
		 public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
		public Project getPrj() {
			return prj;
		}
		public void setPrj(Project prj) {
			this.prj = prj;
		}
		@Transient
		 private String projectId;
		 
		 @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
		 @JoinColumn(name="projectId")
		 @JsonBackReference
		 private Project prj;
		 
		 private int taskId;
		 @OneToMany(fetch=FetchType.EAGER, mappedBy="usr", cascade=CascadeType.ALL)
		 @Fetch(value = FetchMode.SUBSELECT)
		 @JsonManagedReference(value="usr-task")
		 private List<Task> tasks;
		 
		 public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getEmployeeId() {
			return employeeId;
		 }
		 public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		 }
		 public String getFirstName() {
			return firstName;
		 }
		 public void setFirstName(String firstName) {
			this.firstName = firstName;
		 }
		
		public int getTaskId() {
			return taskId;
		}
		public void setTaskId(int taskId) {
			this.taskId = taskId;
		}
		public List<Task> getTasks() {
			return tasks;
		}
		public void setTasks(List<Task> tasks) {
			this.tasks = tasks;
		}
		
}
