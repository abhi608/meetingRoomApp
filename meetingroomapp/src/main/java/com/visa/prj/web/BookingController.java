package com.visa.prj.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.visa.prj.entity.AddBooking;
import com.visa.prj.entity.Booking;
import com.visa.prj.entity.Equipment;
import com.visa.prj.entity.Layout;
import com.visa.prj.entity.Room;
import com.visa.prj.service.AdminService;

@RestController
public class BookingController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="api/bookings",method=RequestMethod.GET)
	public @ResponseBody List<Booking> getBookings() {
		System.out.println(adminService.getAllBookings());
		return adminService.getAllBookings();
	}
	
	@RequestMapping(value="api/room",method=RequestMethod.GET)
	public @ResponseBody List<Room> getRooms(){
		return adminService.getRooms();
	}
	
	@RequestMapping(value="api/layout",method=RequestMethod.GET)
	public @ResponseBody List<Layout> getLayouts(){
		return adminService.getLayouts();
	}
	
	@RequestMapping(value="api/makePending/{id}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateBooking1(@PathVariable("id") int id) {
		adminService.makePending(id);
		return new ResponseEntity<String>("Booking with id " + id + " updated !!!",HttpStatus.OK);
	}
	@RequestMapping(value="api/makeConfirmed/{id}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateBooking2(@PathVariable("id") int id) {
		adminService.makeConfirmed(id);
		return new ResponseEntity<String>("Booking with id " + id + " updated !!!",HttpStatus.OK);
	}
	@RequestMapping(value="api/makeCancelled/{id}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateBooking3(@PathVariable("id") int id) {
		adminService.makeCancelled(id);
		return new ResponseEntity<String>("Booking with id " + id + " updated !!!",HttpStatus.OK);
	}
	
	@RequestMapping(value="api/addbook",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addBooking(@RequestBody AddBooking addBooking) {
		//adminService.addNewBooking(addBooking);
		
		System.out.println(addBooking.toString());
		
		/************addBookings***************/
		Booking booking = new Booking();
		//hourly=1
		//halfday=2 //fullday=3
		
		Date fromDate;
		Date toDate;
		int type = Integer.parseInt(addBooking.getType());
		booking.setType(type);
		booking.setBookingDate(new Date());
		
		
		String justDate = addBooking.getTbdate();
		String fromTime = null;
		String toTime = null;
		if(type==1) {
			fromTime = addBooking.getFromtime();
			toTime = addBooking.getTotime();
		}else if (type==2) {
			String slot= addBooking.getSlot();
		}else {
			
		}
		
		
		
		String fromDatestr = justDate +" "+ fromTime;
		String toDatestr = justDate +" "+ toTime;
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
		try {
			fromDate = format.parse(fromDatestr);
			toDate = format.parse(toDatestr);
			booking.setFromDate(fromDate);
			booking.setToDate(toDate);
			
			System.out.println(fromDate);
			System.out.println(toDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("SUCCESS==TRUE",HttpStatus.CREATED);
	}
	
}
	