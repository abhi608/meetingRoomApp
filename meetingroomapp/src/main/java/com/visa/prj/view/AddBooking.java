package com.visa.prj.view;

import java.util.List;

public class AddBooking {
	
	private String username;
	private String useremail;
	private String useraddress;
	private String userphone;
	private String tbdate;
	private String type;
	private String slot;
	private String fromtime;
	private String totime;
	private String room;
	private String layout;
	private List<String> equipmentIds;
	private List<String> equipmentQty;
	
	
	@Override
	public String toString() {
		return "AddBooking [username=" + username + ", useremail=" + useremail + ", useraddress=" + useraddress
				+ ", userphone=" + userphone + ", tbdate=" + tbdate + ", type=" + type + ", slot=" + slot
				+ ", fromtime=" + fromtime + ", totime=" + totime + ", room=" + room + ", layout=" + layout
				+ ", equipmentIds=" + equipmentIds + ", equipmentQty=" + equipmentQty +"]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getTbdate() {
		return tbdate;
	}
	public void setTbdate(String tbdate) {
		this.tbdate = tbdate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public String getFromtime() {
		return fromtime;
	}
	public void setFromtime(String fromtime) {
		this.fromtime = fromtime;
	}
	public String getTotime() {
		return totime;
	}
	public void setTotime(String totime) {
		this.totime = totime;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	
	public List<String> getEquipmentIds() {
		return equipmentIds;
	}
	public void setEquipmentIds(List<String> equipmentIds) {
		this.equipmentIds = equipmentIds;
	}
	public List<String> getEquipmentQty() {
		return equipmentQty;
	}
	public void setEquipmentQty(List<String> equipmentQty) {
		this.equipmentQty = equipmentQty;
	}
	
	
	
}
