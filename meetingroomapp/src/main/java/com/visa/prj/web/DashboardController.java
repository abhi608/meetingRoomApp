package com.visa.prj.web;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.visa.prj.service.AdminService;
import com.visa.prj.entity.Booking;

@RestController
public class DashboardController {
	
	
	@Autowired
	private AdminService admin;
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public ResponseEntity<String> getDashboard(){
		JSONObject j=new JSONObject();
		List<Booking> sortedBooking=admin.getSortedBookings();
		List<Booking> totalBooking=admin.getTotalBookings();
		List<Booking> bookingToday= admin.getBookingByDate(new Date());
		List<Booking> bookingMadeToday=admin.getBookingMadeByDate(new Date());
		try {
			j.put("sorted_booking", sortedBooking);
			j.put("total_booking", totalBooking.size());
			j.put("booking_today", bookingToday.size() );
			j.put("booking_made_today", bookingMadeToday.size());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(j.toString(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/dashboard" ,method=RequestMethod.POST)
	public ResponseEntity<String> getDateBooking(@RequestBody Date date){
		JSONObject j=new JSONObject();
		List<Booking> bookingByDate= admin.getBookingByDate(date);
		try {
			j.put("booking_by_date", bookingByDate);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(j.toString(),HttpStatus.OK);
	}
}
