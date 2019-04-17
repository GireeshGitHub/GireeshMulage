package com.empmgt.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;

import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.empmgt.vo.EmployeeVo;

/**
 * 
 * @author M1024305
 * Employee management dao impl class
 *
 */
@Repository
@Transactional
public class EmpMgtDaoImpl implements EmpMgtDao {
	
	private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
	
	
	/**
	 * Add employee
	 */
	public Employee addEmployee(EmployeeVo empVo, Session session) throws ParseException, HibernateException {
		// TODO Auto-generated method stub
		Employee emp = mapDAOValues(empVo);
		session.save(emp);
		//session.flush();
		return emp;
	}
	
	private Employee mapDAOValues(EmployeeVo empVo) throws ParseException{
		Employee emp = new Employee();
			emp.setUserName(empVo.getUserName());
			emp.setPassword(empVo.getPassword());
			emp.setFullName(empVo.getFullName());
			emp.setGender(empVo.getGender());
			emp.setEmailId(empVo.getEmailId());
			Date dob = format.parse(empVo.getDateOfBirth());
			emp.setDateOfBirth(dob);
			emp.setSecurityQuestion(empVo.getSecurityQuestion());
			emp.setSecurityAnswer(empVo.getSecurityAnswer());
		
		return emp;
	}

	/**
	 * delete employee by id
	 */
	@Override
	public boolean deleteEmployee(String empId, Session session) throws HibernateException, IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException{
		boolean employeeDeleted = false;
		
		Query qry = session.createQuery("delete from Employee where id = :empId").setParameter("empId", Integer.parseInt(empId));
		int resultSet = qry.executeUpdate();
		if(resultSet>=0){
			employeeDeleted = true;
		}
		return employeeDeleted;
	}

	/**
	 * list all employees
	 */
	@Override
	public List<Employee> listEmployees(Session session) throws HibernateException, IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException {
		return session.createQuery("from Employee").getResultList();
	}

	/**
	 * get employee details by employee id
	 */
	@Override
	public Employee getEmployeeByEmpId(String empId, Session session) throws HibernateException, NoResultException, NonUniqueResultException, IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException  {
		// TODO Auto-generated method stub
		Query qry = session.createQuery("from Employee where id = :empId").setParameter("empId", Integer.parseInt(empId));
		return (Employee) qry.getSingleResult();
	}

	/**
	 * Authenticate employee using username and password
	 */
	@Override
	public boolean checkLogin(EmployeeVo empVo, Session session) throws HibernateException, NoResultException, NonUniqueResultException, IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException  {
		boolean loginValid = false;
		Query qry = session.createQuery("from Employee where userName = :userName and password = :password").setParameter("userName", empVo.getUserName())
				.setParameter("password", empVo.getPassword());
		try{
			Employee employee = (Employee) qry.getSingleResult();
			if(employee!=null){
				loginValid = true;
			}
		}
		catch(NoResultException nre){
			loginValid = false;
		}
		return loginValid;
	}

	
}
