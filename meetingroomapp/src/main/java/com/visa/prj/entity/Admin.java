package com.visa.prj.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admins")
public class Admin {

	@Id
	private String email;

	private String name;

	private String password;
	
	private boolean status;
	
	@Column(name="registration_date")
	private Date registrationDate = new Date();

	public Admin(String name, String email, String password, boolean status, Date registrationDate) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.registrationDate = registrationDate;
	}

	public Admin() {
	}

	

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
