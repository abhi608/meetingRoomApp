package com.visa.prj.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.visa.prj.entity.Booking;



public interface BookingDao extends JpaRepository<Booking, Integer> {

	@Query("from Booking b order by b.bookingDate")
	List<Booking> getSortedBooking();
	
	@Query("from Booking b where DATE(b.fromDate) = DATE(:date) ")
	List<Booking> getBookingByDate(@Param ("date") Date date);
	
	@Query("from Booking b where DATE(b.bookingDate) = DATE(:date) ")
	List<Booking> getBookingMadeByDate(@Param ("date") Date date);

	@Query("SELECT b FROM Booking b JOIN b.room r where r.id= :id ")
	List<Booking> getBookingByRoom(@Param ("id") Integer id);

	@Query("SELECT b from Booking b WHERE b.toDate > :date")
	List<Booking> getBookingsAfterDate(@Param("date") Date date);

}