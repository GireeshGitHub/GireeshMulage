package com.empmgt;

import org.junit.BeforeClass;
import org.junit.Test;

import com.empmgt.utils.CommonConstant;
import com.empmgt.vo.EmployeeVo;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * 
 * @author M1024305
 * Unit test class
 *
 */
public class EmpMgtControllerTest {

	@BeforeClass
	public static void init(){
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8080;
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public void testAddEmp(){
		EmployeeVo employeeVo = new EmployeeVo();
		employeeVo.setUserName("Ram");
		employeeVo.setPassword("Ram");
		employeeVo.setFullName("Ram Laxman");
		employeeVo.setDateOfBirth("05-11-2009");
		employeeVo.setEmailId("ram@gmail.com");
		employeeVo.setGender("M");
		employeeVo.setSecurityQuestion("Whats given name");
		employeeVo.setSecurityAnswer("Ram");
		given().contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON).body(employeeVo).when()
		.put("EmpMgt/addEmp").then().contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
		.body(CommonConstant.STR_STATUS, equalTo(CommonConstant.CONSTANT_200));
	}
	
	@Test
	public void testDeleteEmployee(){
		given().contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON).when()
		.put("EmpMgt/deleteEmp/12").then().contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
		.body(CommonConstant.STR_STATUS, equalTo(CommonConstant.CONSTANT_200));
	}
	
	@Test
	public void testGetAllEmployeeDetails(){
		get("EmpMgt/getAllEmpDetails").then().body("statusCode", equalTo(CommonConstant.CONSTANT_200));
	}
	
	@Test
	public void testGetEmployeeByEmpId(){
		get("EmpMgt/getByEmpId/3").then().body("employeeVo.userName", equalTo("Gireesh"));
	}
	
	@Test
	public void testCheckLogin(){
		EmployeeVo employeeVo = new EmployeeVo();
		employeeVo.setUserName("Gireesh");
		employeeVo.setPassword("Gireesh");
		given().contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON).body(employeeVo).when()
		.post("EmpMgt/checkLogin").then().
		contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
		.body(CommonConstant.STR_STATUS, equalTo(CommonConstant.CONSTANT_200));
	}
}
