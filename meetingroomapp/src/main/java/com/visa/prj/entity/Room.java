package com.visa.prj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rooms")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int capacity;
	private boolean status;
	
	@Column(name="hour_price")
	private double hourPrice;
	
	@Column(name="half_day_price")
	private double halfDayPrice;
	
	@Column(name="full_day_price")
	private double fullDayPrice;
	
	private String description;
	
	@Column(name="img_src")
	private String imgSrc;
	
	public Room() {
	}


	public Room(int id, String name, int capacity, boolean status, double hourPrice, double halfDayPrice,
			double fullDayPrice, String description, String imgSrc) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.status = status;
		this.hourPrice = hourPrice;
		this.halfDayPrice = halfDayPrice;
		this.fullDayPrice = fullDayPrice;
		this.description = description;
		this.imgSrc = imgSrc;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImgSrc() {
		return imgSrc;
	}


	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
}
