package com.visa.prj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.visa.prj.entity.Booking;

public interface BookingDao extends JpaRepository<Booking, Integer> {

}
