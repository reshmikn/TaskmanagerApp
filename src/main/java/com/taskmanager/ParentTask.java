package com.taskmanager;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "parenttask")
public class ParentTask {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="ParentId")
	 private int parentId;
	 @Column(name="ParentTask")
	 private String parentTask;
	 
	 @OneToMany(fetch=FetchType.EAGER, mappedBy="pTask", cascade=CascadeType.ALL)
	 @JsonManagedReference(value="par-task")
	 private List<Task> tasks;
	 
	
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getParentTask() {
		return parentTask;
	}
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	

}
