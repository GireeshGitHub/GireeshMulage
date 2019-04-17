package com.empmgt.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReturnMessage {

	private String statusCode;
	private String status;
	private String message;
	private EmployeeVo employeeVo;
	private List<EmployeeVo> listEmployeeVo;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public EmployeeVo getEmployeeVo() {
		return employeeVo;
	}
	public void setEmployeeVo(EmployeeVo employeeVo) {
		this.employeeVo = employeeVo;
	}
	public List<EmployeeVo> getListEmployeeVo() {
		return listEmployeeVo;
	}
	public void setListEmployeeVo(List<EmployeeVo> listEmployeeVo) {
		this.listEmployeeVo = listEmployeeVo;
	}
	
	
}
