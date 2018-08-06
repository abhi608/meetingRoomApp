package com.visa.prj.entity;

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

	public Admin(String name, String email, String password, boolean status) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
	}

	public Admin() {
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
