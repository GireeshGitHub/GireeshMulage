package com.empmgt.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.empmgt.dao.DAOFactory;
import com.empmgt.dao.EmpMgtDao;
import com.empmgt.dao.Employee;
import com.empmgt.utils.CommonConstant;
import com.empmgt.vo.EmployeeVo;

/**
 * 
 * @author M1024305
 * Service class
 */
@Component
public class EmpMgtService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Employee addEmployee(EmployeeVo empVo) throws ParseException, HibernateException, IllegalStateException, RollbackException{
		EmpMgtDao empMgtDao = DAOFactory.getDAOFactory(CommonConstant.STR_MYSQL).getEmpMgtDao();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();		
		Employee emp= empMgtDao.addEmployee(empVo, session);
		transaction.commit();
		return emp;
	}
	
	public boolean deleteEmployee(String empId) throws  HibernateException, IllegalStateException, RollbackException, TransactionRequiredException, QueryTimeoutException, PersistenceException{
		EmpMgtDao empMgtDao = DAOFactory.getDAOFactory(CommonConstant.STR_MYSQL).getEmpMgtDao();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();		
		boolean deleteResult= empMgtDao.deleteEmployee(empId, session);
		transaction.commit();
		return deleteResult;
	}
	
	private Session getSession(){
		Session session;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		return session;
	}

	public List<EmployeeVo> listEmployee() throws HibernateException, IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException{
		// TODO Auto-generated method stub
		EmpMgtDao empMgtDao = DAOFactory.getDAOFactory(CommonConstant.STR_MYSQL).getEmpMgtDao();
		Session session = getSession();
		session.beginTransaction();	
		List<Employee> employeeList = empMgtDao.listEmployees(session);
		return tranformDtoToVo(employeeList);
	}
	
	public EmployeeVo getEmployeeByEmpId(String empId) throws HibernateException, NoResultException, NonUniqueResultException, IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException{
		EmpMgtDao empMgtDao = DAOFactory.getDAOFactory(CommonConstant.STR_MYSQL).getEmpMgtDao();
		Session session = getSession();
		session.beginTransaction();
		Employee emp = empMgtDao.getEmployeeByEmpId(empId, session);
		return transformToVo(emp);
	}
	private List<EmployeeVo> tranformDtoToVo(List<Employee> employeeList){
		List<EmployeeVo> listEmpVo = new ArrayList<EmployeeVo>();
		for(Employee employee: employeeList){
			EmployeeVo empVo = new EmployeeVo();
			empVo.setUserName(employee.getUserName());
			empVo.setFullName(employee.getFullName());
			empVo.setDateOfBirth(employee.getDateOfBirth().toString());
			empVo.setEmailId(employee.getEmailId());
			empVo.setGender(employee.getGender());
			listEmpVo.add(empVo);
			
		}
		return listEmpVo;
	}
	
	private EmployeeVo transformToVo(Employee employee){
		EmployeeVo empVo = new EmployeeVo();
		empVo.setUserName(employee.getUserName());
		empVo.setFullName(employee.getFullName());
		empVo.setDateOfBirth(employee.getDateOfBirth().toString());
		empVo.setEmailId(employee.getEmailId());
		empVo.setGender(employee.getGender());
		return empVo;
	}

	public boolean checkLogin(EmployeeVo empVo) throws HibernateException, NoResultException, NonUniqueResultException, IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException {
		EmpMgtDao empMgtDao = DAOFactory.getDAOFactory(CommonConstant.STR_MYSQL).getEmpMgtDao();
		Session session = getSession();
		session.beginTransaction();
		return empMgtDao.checkLogin(empVo, session);
		
	}
}
