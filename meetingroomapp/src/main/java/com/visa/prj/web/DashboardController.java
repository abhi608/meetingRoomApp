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
import com.visa.prj.entity.AddBooking;
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
	public ResponseEntity<String> submitStep(@RequestBody Map<String, Object> data, @PathVariable("step") int step) {
		if(step == 1) {
			try {
				ses.setAttribute("roomId", (String) data.get("roomId"));
				return new ResponseEntity<String>((String) data.get("roomId") + "selected",HttpStatus.OK);
			} catch (Exception e) {
				return null;
			}
		} else if(step == 2) {
			try {
				System.out.println("roomId: " + ses.getAttribute("roomId"));
				ses.setAttribute("type", (String) data.get("type"));
				ses.setAttribute("slot", (String) data.get("slot"));
				ses.setAttribute("fromtime", (String) data.get("fromtime"));
				ses.setAttribute("totime", (String) data.get("totime"));
				ses.setAttribute("tbdate", (String) data.get("tbdate"));
				return new ResponseEntity<String>("step " + step + " completed successfully" ,HttpStatus.OK);
				
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		} else if(step == 3) {
			try {
				System.out.println("roomId: " + ses.getAttribute("roomId") + ses.getAttribute("type"));
				ses.setAttribute("layout", (String) data.get("layout"));
				ses.setAttribute("equipmentIds", (List<String>) data.get("equipmentIds"));
				ses.setAttribute("equipmentQty", (List<String>) data.get("equipmentQty"));
				System.out.println(ses.getAttribute("equipmentIds"));
				System.out.println(ses.getAttribute("equipmentQty"));
				return new ResponseEntity<String>("step " + step + " completed successfully" ,HttpStatus.OK);
				
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}
		return null;
	}
	
	@RequestMapping(value="api/final", method=RequestMethod.POST)
	public ResponseEntity<AddBooking> submitFinal(@RequestBody Map<String, Object> data) {
		try {
			System.out.println("roomId: " + ses.getAttribute("roomId"));
			System.out.println(ses.getAttribute("type"));
			System.out.println(ses.getAttribute("equipmentIds"));
			System.out.println(ses.getAttribute("equipmentQty"));
			ses.setAttribute("username", (String) data.get("username"));
			ses.setAttribute("useraddress", (String) data.get("useraddress"));
			ses.setAttribute("userphone", (String) data.get("userphone"));
			ses.setAttribute("useremail", (String) data.get("useremail"));
			
			AddBooking b = new AddBooking();
			b.setUseremail((String) ses.getAttribute("useremail"));
			b.setUserphone((String) ses.getAttribute("userphone"));
			b.setUseraddress((String) ses.getAttribute("useraddress"));
			b.setUsername((String) ses.getAttribute("username"));
			b.setEquipmentIds((List<String>) ses.getAttribute("equipmentQty"));
			b.setEquipmentQty((List<String>) ses.getAttribute("equipmentIds"));
			b.setLayout((String) ses.getAttribute("layout"));
			b.setType((String) ses.getAttribute("type"));
			b.setSlot((String) ses.getAttribute("slot"));
			b.setFromtime((String) ses.getAttribute("fromtime"));
			b.setTotime((String) ses.getAttribute("totime"));
			b.setTbdate((String) ses.getAttribute("tbdate"));
			b.setRoom((String) ses.getAttribute("roomId"));
			return new ResponseEntity<AddBooking>(b, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
