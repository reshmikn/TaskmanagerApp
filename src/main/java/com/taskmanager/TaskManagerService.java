package com.taskmanager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("taskManagerService")
public class TaskManagerService {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	//@Transactional
	public Task addTask(Task tsk) {
	
		List<Task> taskList = new ArrayList<Task>();
		ParentTask parentTsk = new ParentTask();
		parentTsk.setParentTask(tsk.getParentTask());
		tsk.setpTask(parentTsk);
		taskList.add(tsk);
		parentTsk.setTasks(taskList);
		Project project=(Project) projectService.findById(tsk.getProjectId());
		tsk.setProjt(project);
		User user = (User) userService.findById(tsk.getUserId());
		tsk.setUsr(user);
		
	//	tsk = new Task(1,1,"New Task", new Date(1/1/2010), new Date(1/1/2019), 1);
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		session.save(parentTsk);
		tx.commit();
		return tsk;
		
	}

	public List<Task> viewTask(int projectId) {
		List <Task> taskList = new ArrayList<Task>();
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		/*
		 * Criteria c = session.createCriteria(ParentTask .class,"ptsk");
		 * c.createCriteria("tasks", "s");//Teacher class should contain students
		 * collection //c.add(Restrictions.eq("s.name", "Ashok"));
		 */	
		Criteria c = session.createCriteria(ParentTask.class)
		      .add(Restrictions.sqlRestriction("exists (select 1 from task t where t.parentId = {alias}.parentId and t.projectId=projectId)"));
             List<ParentTask> ptasklist=c.list();
             
             for(ParentTask parTask : ptasklist ) {
            	 String tempPTaskName =parTask.getParentTask();
            	List <Task> newTaskList = parTask.getTasks();
            	for(Task teTask :newTaskList) {
            		teTask.setParentTask(tempPTaskName);
            		taskList.add(teTask);
            	}
             }
             tx.commit();
		return taskList;
	}

	public Object findById(int taskId) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		 Task tsk = (Task) session.get(Task.class, taskId);
		 ParentTask psk =tsk.getpTask();
		 String parentTaskString= psk.getParentTask();
		 tsk.setParentTask(parentTaskString);
         session.getTransaction().commit();
		return tsk;
	}

	public Task save(Task task) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		session.update(task);
		tx.commit();
		return task;
	}

	public void delete(Task task) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		session.delete(task); 
		tx.commit();
		
	}
	
}
