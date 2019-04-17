package com.empmgt.controller;

import java.text.ParseException;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.empmgt.dao.Employee;
import com.empmgt.service.EmpMgtService;
import com.empmgt.utils.CommonConstant;
import com.empmgt.vo.EmployeeVo;
import com.empmgt.vo.ReturnMessage;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author M1024305
 * EmpMgtController is used to add, delete, get employee details.
 *
 */
@Controller
@Path("/")
public class EmpMgtController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpMgtController.class);
	
	@Autowired
	EmpMgtService empService;
	
	/**
	 * 
	 * @param empVo
	 * @return
	 * add employee
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(CommonConstant.ADD_EMPLOYEE_URL)
	public ReturnMessage addEmployee(EmployeeVo empVo){
		ReturnMessage reply = new ReturnMessage();
		try{
			Employee employee= empService.addEmployee(empVo);
			if(employee!=null){
				reply.setMessage("Employee data inserted successfully.");
				reply.setStatusCode(CommonConstant.CONSTANT_200);
				reply.setStatus(CommonConstant.STR_SUCCESS);
			}
			else{
				reply.setMessage(CommonConstant.STR_DATA_MISMATCH);
				reply.setStatusCode(CommonConstant.CONSTANT_400);
				reply.setStatus(CommonConstant.STR_FAILURE);
			}
		}
		catch(ParseException pex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_PARSE_EX, pex);
		}
		catch(HibernateException hex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_HIBERNATE_EX, hex);
		}
		catch(IllegalStateException iex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_ILLEGAL_ST_EX, iex);
		}
		catch(RollbackException ex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_ROLLBACK_EX, ex);
		}
		return reply;
	}
	
	/**
	 * 
	 * @param empId
	 * @return
	 * delete employee
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(CommonConstant.DELETE_EMP_URL)
	public ReturnMessage deleteEmployee(@PathParam("empId") String empId){
		ReturnMessage reply = new ReturnMessage();
		try{
			boolean employeeDeleted = empService.deleteEmployee(empId);
			if(employeeDeleted){
				reply.setMessage("Employee data deleted successfully");
				reply.setStatusCode(CommonConstant.CONSTANT_200);
				reply.setStatus(CommonConstant.STR_SUCCESS);
			}
			else{
				reply.setMessage(CommonConstant.STR_DATA_MISMATCH);
				reply.setStatusCode(CommonConstant.CONSTANT_400);
				reply.setStatus(CommonConstant.STR_FAILURE);
			}
		}
		catch(HibernateException hex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_HIBERNATE_EX, hex);
		}
		catch(IllegalStateException iex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_ILLEGAL_ST_EX, iex);
		}
		catch(RollbackException ex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_ROLLBACK_EX, ex);
		}
		catch(TransactionRequiredException tex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_TRX_EX, tex);
		}
		catch(QueryTimeoutException qex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_TIMEOUT_EX, qex);
		}
		catch(PersistenceException pex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_PERSIST_EX, pex);
		}
		return reply;
	}
	
	/**
	 * 
	 * @return
	 * Get all employee details
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(CommonConstant.GET_EMP_DET_URL)
	public ReturnMessage getAllEmployeeDetails(){
		ReturnMessage reply = new ReturnMessage();
		try{
			List<EmployeeVo> empList = empService.listEmployee();
			if(empList!=null && empList.size()>0){
				reply.setMessage("");
				reply.setStatusCode(CommonConstant.CONSTANT_200);
				reply.setStatus(CommonConstant.STR_SUCCESS);
				reply.setListEmployeeVo(empList);
			}
		}
		catch(HibernateException hex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_HIBERNATE_EX, hex);
		}
		catch(IllegalStateException iex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_ILLEGAL_ST_EX, iex);
		}
		catch(TransactionRequiredException tex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_TRX_EX, tex);
		}
		catch(QueryTimeoutException qex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_TIMEOUT_EX, qex);
		}
		catch(PersistenceException pex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_PERSIST_EX, pex);
		}
		
		return reply;
	}
	
	/**
	 * 
	 * @param empId
	 * @return
	 * Get employee details by employee id
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(CommonConstant.GET_BY_EMP_ID_URL)
	public ReturnMessage getEmployeeByEmpId(@PathParam("empId") String empId){
		ReturnMessage reply = new ReturnMessage();
		try{
			EmployeeVo empVo = empService.getEmployeeByEmpId(empId);
			if(empVo!=null){
				reply.setMessage("");
				reply.setStatusCode(CommonConstant.CONSTANT_200);
				reply.setStatus(CommonConstant.STR_SUCCESS);
				reply.setEmployeeVo(empVo);
			}
			else{
				reply.setMessage(CommonConstant.STR_DATA_MISMATCH);
				reply.setStatusCode(CommonConstant.CONSTANT_400);
				reply.setStatus(CommonConstant.STR_FAILURE);
			}
		}
		catch(HibernateException hex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_HIBERNATE_EX, hex);
		}
		catch(IllegalStateException iex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_ILLEGAL_ST_EX, iex);
		}
		catch(TransactionRequiredException tex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_TRX_EX, tex);
		}
		catch(QueryTimeoutException qex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_TIMEOUT_EX, qex);
		}
		catch(PersistenceException pex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_PERSIST_EX, pex);
		}
		return reply;
	}
	
	/**
	 * 
	 * @param empVo
	 * @return
	 * authenticate employee using username and password
	 */
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(CommonConstant.CHECK_LOGIN_URL)
	public ReturnMessage checkLogin(EmployeeVo empVo){
		ReturnMessage reply = new ReturnMessage();
		try{
			boolean validLogin = empService.checkLogin(empVo);
			
			if(validLogin){
				reply.setMessage("Employee has authenticated successfully");
				reply.setStatusCode(CommonConstant.CONSTANT_200);
				reply.setStatus(CommonConstant.STR_SUCCESS);
			}
			else{
				reply.setMessage("Invalid Username and Password.");
				reply.setStatusCode(CommonConstant.CONSTANT_302);
				reply.setStatus(CommonConstant.STR_SUCCESS);
			}
		}
		catch(HibernateException hex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_HIBERNATE_EX, hex);
		}
		catch(IllegalStateException iex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_ILLEGAL_ST_EX, iex);
		}
		catch(TransactionRequiredException tex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_TRX_EX, tex);
		}
		catch(QueryTimeoutException qex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_TIMEOUT_EX, qex);
		}
		catch(PersistenceException pex){
			reply.setMessage(CommonConstant.STR_SERVER_ERROR);
			reply.setStatusCode(CommonConstant.CONSTANT_500);
			reply.setStatus(CommonConstant.STR_FAILURE);
			LOGGER.error(CommonConstant.STR_PERSIST_EX, pex);
		}
		return reply;
	}
	
	

}
