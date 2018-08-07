package com.visa.prj.entity;

import java.util.List;

public class DashBoard {
	private List<Booking> sortedBooking;
	private int totalBookingCount;
	private int bookingMadeToday;
	private int bookingForToday;
	
	
	
	
	public DashBoard() {
		super();
	}
	public List<Booking> getSortedBooking() {
		return sortedBooking;
	}
	public void setSortedBooking(List<Booking> sortedBooking) {
		this.sortedBooking = sortedBooking;
	}
	public int getTotalBookingCount() {
		return totalBookingCount;
	}
	public void setTotalBookingCount(int totalBookingCount) {
		this.totalBookingCount = totalBookingCount;
	}
	public int getBookingMadeToday() {
		return bookingMadeToday;
	}
	public void setBookingMadeToday(int bookingMadeToday) {
		this.bookingMadeToday = bookingMadeToday;
	}
	public int getBookingForToday() {
		return bookingForToday;
	}
	public void setBookingForToday(int bookingForToday) {
		this.bookingForToday = bookingForToday;
	}
	
	
	
}
