package com.taskmanager;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "task")

public class Task {
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="taskId")
	 private int taskId;
	/*
	 * @Column(name="parentId") private int parentId;
	 */
	 @Column(name="taskName")
	 private String task;
	 @Column(name="startDate")
	 @Temporal(TemporalType.DATE)
	 private Date startDate;
	 @Column(name="endDate")
	 @Temporal(TemporalType.DATE)
	 private Date endDate;
	
	 @Column(name="priority")
	 private int priority = 0;
	 
	@Transient
	 private String parentTask;
	 
	 @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	 @JoinColumn(name="parentId")
	 @JsonBackReference(value="par-task")
	 private ParentTask pTask;
	 
	 @Transient
	 private int projectId;
	 @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	 @JoinColumn(name="projectId")
	 @JsonBackReference(value="prj-task")
	 private Project projt;
	 
	 @Transient
	 private int userId;
	 
	 @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	 @JoinColumn(name="userId")
	 @JsonBackReference(value="usr-task")
	 private User usr;
	 
	 public Task() {
			super();
		}
		public Task(int taskId, String taskName,Date stDate, Date edDate, int priorityNum) {
			super();
			this.taskId=taskId;
			//this.parentId=parentId;
			this.task=taskName;
			this.startDate=stDate;
			this.endDate=edDate;
			this.priority=priorityNum;
			
		}
	 
	 public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/*
	 * public int getParentId() { return parentId; } public void setParentId(int
	 * parentId) { this.parentId = parentId; }
	 */
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
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
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public User getUsr() {
		return usr;
	}
	public void setUsr(User usr) {
		this.usr = usr;
	}
	public String getParentTask() {
		return parentTask;
	}
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	public ParentTask getpTask() {
		return pTask;
	}
	public void setpTask(ParentTask pTask) {
		this.pTask = pTask;
	}

	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public Project getProjt() {
		return projt;
	}
	public void setProjt(Project projt) {
		this.projt = projt;
	}

	
}
