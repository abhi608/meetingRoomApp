package com.visa.prj.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.visa.prj.service.AdminService;
import com.visa.prj.entity.Booking;
import com.visa.prj.entity.ClientBooking;
import com.visa.prj.entity.DashBoard;

@RestController
public class DashboardController {
	
	
	@Autowired
	private AdminService adminService;
	
	@Autowired 
	 private HttpSession ses;
	
	@RequestMapping(value="api/dashboard",method=RequestMethod.GET)
	public DashBoard getDashboard(){
		DashBoard d=new DashBoard();
		
		List<Booking> sortedBooking=adminService.getSortedBookings();
		List<Booking> totalBooking=adminService.getAllBookings();
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
	
	@RequestMapping(value="api/step/{step}", method=RequestMethod.POST)
	public ResponseEntity<String> submitStep(@RequestBody Map<String, Object> data, @PathVariable("step") int step, HttpServletRequest req) {
		if(step == 1) {
			try {
				System.out.println("ses: " + ses);
				int roomId = Integer.parseInt((String) data.get("roomId"));
				System.out.println("roomid: " + roomId);
				HttpSession ses = req.getSession();
				ses.setAttribute("roomId", roomId);
				return new ResponseEntity<String>((String) data.get("roomId") + "selected",HttpStatus.OK);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
}
