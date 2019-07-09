package com.taskmanager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserService {



	@Autowired
	private SessionFactory sessionFactory;
	
	//@Transactional
	public User addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		
		session.save(user);
		tx.commit();
		return user;
		
	}

	public List<User> viewUser() {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		Query<User> query = session.createQuery("from User"); 

		 List<User> users = query.list(); 
		
		tx.commit();
		return users;
		
	}
	
	public Object findById(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		 User user = (User) session.get(User.class, userId);
		
         session.getTransaction().commit();
		return user;
	}
	
	public User save(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		session.update(user);
		tx.commit();
		return user;
	}

	public void delete(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx= session.beginTransaction();
		session.delete(user); 
		tx.commit();
		
	}


}
