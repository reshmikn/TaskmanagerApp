package com.taskmanager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectService")
public class ProjectService {

	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired UserService userService;
	//@Transactional
	public Project addProject(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
		
		
		List<User> userList = new ArrayList<User>();
		User user =(User) userService.findById(project.getUserId());
		user.setPrj(project);
		userList.add(user);
		project.setUsers(userList);
		Transaction tx= session.beginTransaction();
	//	tsk = new Task(1,1,"New Task", new Date(1/1/2010), new Date(1/1/2019), 1);
		session.save(project);
		tx.commit();
		return project;
		
	}
	
	public List<Project> viewProject() {
		/*
		 * List <Project> projectList = new ArrayList<Project>(); Session session =
		 * this.sessionFactory.getCurrentSession(); Transaction tx=
		 * session.beginTransaction();
		 * 
		 * Criteria c = session.createCriteria(ParentTask.class) .add(Restrictions.
		 * sqlRestriction("exists (select 1 from project p where p.userId = {alias}.userId)"
		 * )); List<User> userList=c.list();
		 * 
		 * for(User user : userList ) { Project proj =user.getPrj(); List <Task>
		 * newTaskList = parTask.getTasks(); for(Task teTask :newTaskList) {
		 * teTask.setParentTask(tempPTaskName); taskList.add(teTask); } } tx.commit();
		 * return taskList;
		 */
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		Query<Project> query = session.createQuery("from Project"); 

		 List<Project> projects = query.list(); 
		
		tx.commit();
		return projects;
	}
	
	public Object findById(int projectId) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		 Project project = (Project) session.get(Project.class, projectId);
		
         session.getTransaction().commit();
		return project;
	}
	
	public Project save(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		session.update(project);
		tx.commit();
		return project;
	}
	
	public void delete(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		session.delete(project); 
		tx.commit();
		
	}
}
