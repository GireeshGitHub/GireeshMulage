package com.empmgt.dao;

import java.text.ParseException;
import java.util.List;


import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;

import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;

import org.hibernate.HibernateException;
import org.hibernate.Session;


import com.empmgt.vo.EmployeeVo;


public interface EmpMgtDao {

	 Employee addEmployee(EmployeeVo empVo, Session session) throws ParseException, HibernateException;
	
	 boolean deleteEmployee(String empId, Session session) throws HibernateException, IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException;

	 List<Employee> listEmployees(Session session) throws HibernateException, IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException;

	 Employee getEmployeeByEmpId(String empId, Session session)throws HibernateException, NoResultException, NonUniqueResultException, IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException;

	 boolean checkLogin(EmployeeVo empVo, Session session) throws HibernateException, NoResultException, NonUniqueResultException, IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException;
}
