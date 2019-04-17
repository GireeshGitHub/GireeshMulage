package com.empmgt.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

	@Id
	@Column(name="id")
	private int id;
	@Column(name="user_name")	
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="full_name")
	private String fullName;
	@Column(name="emailid")
	private String emailId;
	@Column(name="dob")
	private Date dateOfBirth;
	@Column(name="gender")
	private String gender;
	@Column(name="security_question")
	private String securityQuestion;
	@Column(name="security_answer")
	private String securityAnswer;
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((securityAnswer == null) ? 0 : securityAnswer.hashCode());
		result = prime * result + ((securityQuestion == null) ? 0 : securityQuestion.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;}
		Employee other = (Employee) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null){
				return false;}
		} else if (!dateOfBirth.equals(other.dateOfBirth)){
			return false;}
		if (emailId == null) {
			if (other.emailId != null){
				return false;}
		} else if (!emailId.equals(other.emailId)){
			return false;}
		if (fullName == null) {
			if (other.fullName != null){
				return false;}
		} else if (!fullName.equals(other.fullName)){
			return false;}
		if (gender == null) {
			if (other.gender != null){
				return false;}
		} else if (!gender.equals(other.gender)){
			return false;}
		if (id != other.id){
			return false;}
		if (password == null) {
			if (other.password != null){
				return false;}
		} else if (!password.equals(other.password)){
			return false;}
		if (securityAnswer == null) {
			if (other.securityAnswer != null){
				return false;}
		} else if (!securityAnswer.equals(other.securityAnswer)){
			return false;}
		if (securityQuestion == null) {
			if (other.securityQuestion != null){
				return false;}
		} else if (!securityQuestion.equals(other.securityQuestion)){
			return false;}
		if (userName == null) {
			if (other.userName != null){
				return false;}
		} else if (!userName.equals(other.userName)){
			return false;}
		return true;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	
}
