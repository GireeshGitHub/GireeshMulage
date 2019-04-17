package com.empmgt.dao;

public class HibernateDAOImpl extends DAOFactory{

	@Override
	public EmpMgtDao getEmpMgtDao() {
		// TODO Auto-generated method stub
		return new EmpMgtDaoImpl();
	}

}
