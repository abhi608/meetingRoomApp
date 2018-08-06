package com.visa.prj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.visa.prj.entity.Booking;



public interface BookingDao extends JpaRepository<Booking, Integer> {

	@Query("from Booking b orderby b.bookingDate")
	List<Booking> getSortedBooking();
	
	


}