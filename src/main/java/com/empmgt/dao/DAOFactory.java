package com.empmgt.dao;

public abstract class DAOFactory {
	
	public static DAOFactory getDAOFactory(String factoryNumber){
		if(factoryNumber.equalsIgnoreCase("MySQL")){
		return new HibernateDAOImpl();		
		}
		return null;
	}
	
	public abstract EmpMgtDao getEmpMgtDao();

}
