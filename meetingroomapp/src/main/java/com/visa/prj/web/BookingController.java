package com.visa.prj.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.visa.prj.entity.Booking;
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
	
	
	

}
	