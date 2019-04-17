package com.empmgt.dao;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class HibernateUtil {
	
	private SessionFactory sessionFactory;
	
	public HibernateUtil(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public HibernateUtil(){
		
	}

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	/**
	 * Get the session object.
	 * 
	 * @return
	 */
	protected Session getSession() {
		/**
		 * Initialize the session object
		 */
		Session session = null;
		session = this.sessionFactory.openSession();
		
		return session;
	}
	
	/**
	 * Close DB connection .
	 * Close the session object.
	 * @param session
	 */
	protected void closeSession(Session session) throws IOException{
		/**
		 * Check for session object null value.
		 */
		
			/**
			 * Close the session object.
			 */
			session.close();
		
	}
}
