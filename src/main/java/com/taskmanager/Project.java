package com.taskmanager;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Project")
public class Project {

	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="projectId")
	 private int projectId;
	 @Column(name="projectName")
	 private String projectName;
	 @Column(name="startDate")
	 @Temporal(TemporalType.DATE)
	 private Date startDate;
	 @Column(name="endDate")
	 @Temporal(TemporalType.DATE)
	 private Date endDate;
	 
	 @Column(name="priority")
	 private int priority = 0;
	 

	private int userId;
	 @OneToMany(fetch=FetchType.EAGER, mappedBy="prj", cascade=CascadeType.ALL)
	 @JsonManagedReference
	 private List<User> users;
		
	 
	 private int taskId;
	 @OneToMany(fetch=FetchType.EAGER, mappedBy="projt", cascade=CascadeType.ALL)
	 @Fetch(value = FetchMode.SUBSELECT)
	 @JsonManagedReference(value="prj-task")
	 private List<Task> tasks;
	 
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

		public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	
		 
		
}
