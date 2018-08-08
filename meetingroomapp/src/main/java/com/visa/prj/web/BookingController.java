package com.visa.prj.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		System.out.println(adminService.getTotalBookings2().size());
		return adminService.getTotalBookings();
	}
	
	@RequestMapping(value="api/room",method=RequestMethod.GET)
	public @ResponseBody List<Room> getRooms(){
		return adminService.getRooms();
	}
	
	@RequestMapping(value="api/layout",method=RequestMethod.GET)
	public @ResponseBody List<Layout> getLayouts(){
		return adminService.getLayouts();
	}
	
	
	

}
	