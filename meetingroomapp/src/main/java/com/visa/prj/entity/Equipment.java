package com.visa.prj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="equipment")
public class Equipment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int equipId;
	
	@Column(name="name",length=100,nullable=false)
	private String name;
	
	private double price;
	
	@Column(name="qty")
	private int quantity;
	
	@Version
	@Column(name="version_no")
	private int versionNo;

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

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + equipId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		result = prime * result + versionNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		if (equipId != other.equipId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (versionNo != other.versionNo)
			return false;
		return true;
	}

	public Equipment(int equipId, String name, double price, int quantity, int versionNo) {
		super();
		this.equipId = equipId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.versionNo = versionNo;
	}

	public Equipment() {
		super();
	}

	@Override
	public String toString() {
		return "Equipment [equipId=" + equipId + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", versionNo=" + versionNo + "]";
	}
	
}s