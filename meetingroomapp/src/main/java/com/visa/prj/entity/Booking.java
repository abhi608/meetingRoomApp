package com.visa.prj.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="bookings")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="booking_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bookingDate= new Date();
	
	@ManyToOne
	@JoinColumn(name="room_fk")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name="layout_fk")
	private Layout layout;
	
	@ManyToOne
	@JoinColumn(name="client_fk")
	private Client client;
	
	private int type;
	
	private int status;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="equip_line_item_fk")
	private List<EquipmentLineItem> equipLineItem;
	
	@Column(name="from_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;
	
	@Column(name="to_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<EquipmentLineItem> getEquipLineItem() {
		return equipLineItem;
	}

	public void setEquipLineItem(List<EquipmentLineItem> equipLineItem) {
		this.equipLineItem = equipLineItem;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	
}
