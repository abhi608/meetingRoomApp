package com.visa.prj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="equipment")
public class Equipment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uquip_id")
	private int equipId;
	
	private String name;
	
	private double price;
	
	private int quantity;

	public int getEquipId() {
		return equipId;
	}

	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Equipment(int equipId, String name, double price, int quantity) {
		this.equipId = equipId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Equipment() {
	}

	
}