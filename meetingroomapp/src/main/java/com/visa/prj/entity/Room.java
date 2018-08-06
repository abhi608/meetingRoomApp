package com.visa.prj.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int capacity;
	private boolean status;
	private double hourPrice;
	private double halfDayPrice;
	private double fullDayPrice;
	
	
	public Room() {
	}


	public Room(int id, String name, int capacity, boolean status, double hourPrice, double halfDayPrice,
			double fullDayPrice) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.status = status;
		this.hourPrice = hourPrice;
		this.halfDayPrice = halfDayPrice;
		this.fullDayPrice = fullDayPrice;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public double getHourPrice() {
		return hourPrice;
	}


	public void setHourPrice(double hourPrice) {
		this.hourPrice = hourPrice;
	}


	public double getHalfDayPrice() {
		return halfDayPrice;
	}


	public void setHalfDayPrice(double halfDayPrice) {
		this.halfDayPrice = halfDayPrice;
	}


	public double getFullDayPrice() {
		return fullDayPrice;
	}


	public void setFullDayPrice(double fullDayPrice) {
		this.fullDayPrice = fullDayPrice;
	}
	
	
	
	
	

}
