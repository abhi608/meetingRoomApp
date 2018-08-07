package com.visa.prj.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.visa.prj.service.AdminService;
import com.visa.prj.entity.Booking;
import com.visa.prj.entity.DashBoard;

@RestController
public class DashboardController {
	
	
	@Autowired
	private AdminService adminService;
	@RequestMapping(value="api/dashboard",method=RequestMethod.GET)
	public DashBoard getDashboard(){
		DashBoard d=new DashBoard();
		
		List<Booking> sortedBooking=adminService.getSortedBookings();
		List<Booking> totalBooking=adminService.getTotalBookings();
		List<Booking> bookingToday= adminService.getBookingByDate(new Date());
		List<Booking> bookingMadeToday=adminService.getBookingMadeByDate(new Date());
		
			d.setSortedBooking(sortedBooking);
			d.setTotalBookingCount(totalBooking.size());
			d.setBookingForToday(bookingToday.size());			
			d.setBookingMadeToday(bookingMadeToday.size());
		
		return d;
	}
	
	@RequestMapping(value="api/dashboard" ,method=RequestMethod.POST)
	public List<Booking> getDateBooking(@RequestBody Map<String, Object> date){
		Date d;
		System.out.println(date.get("date"));
		try {
			d = new SimpleDateFormat("dd/MM/yy").parse((String) date.get("date"));
			System.out.println(d.toString());
			return  adminService.getBookingByDate(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
}
