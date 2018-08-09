package com.visa.prj.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.visa.prj.dao.BookingDao;
import com.visa.prj.entity.Booking;
import com.visa.prj.entity.Room;
import com.visa.prj.service.AdminService;

@RestController
public class MeetingRoomController {

	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private BookingDao bookingDao;
	
	@RequestMapping(value="api/getMeetingRooms",method=RequestMethod.GET)
	public @ResponseBody List<Room> getRooms() {
		return adminService.getRooms();
	}
	
	@RequestMapping(value="api/getActiveRooms",method=RequestMethod.GET)
	public @ResponseBody List<Room> getActiveRooms() {
		try {
			return adminService.getActiveRooms();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	@RequestMapping(value="api/changeRoomStatus/{id}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateRoom(@PathVariable("id") Integer id) {
		adminService.changeRoomStatus(id);
		return new ResponseEntity<String>("Room with id " + id + " updated !!!",HttpStatus.OK);
	}
	
	@RequestMapping(value="api/room/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteRoom(@PathVariable("id") int id) {
		List<Booking > bk= bookingDao.getBookingByRoom(id);
		
		// If no booking for this room, then delete the room
		if(bk==null) {
			adminService.deleteRoomById(id);
			return null;
		}else {
			return new ResponseEntity<String>("Room cannot be deleted as it has bookings present !!!",HttpStatus.OK);
		}
	}
}
