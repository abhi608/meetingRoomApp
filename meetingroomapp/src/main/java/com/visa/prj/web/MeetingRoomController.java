package com.visa.prj.web;

import java.util.Date;
import java.util.List;

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
		int c=0;
		//System.out.println(new Date());
		for(Booking b:bk) {
//			System.out.println(b.getToDate());
//			System.out.println(new Date());
			//System.out.println(b.getFromDate().compareTo(new Date())>0);
			
			//cannot delete the room
			
			//future booking
			if((b.getFromDate().compareTo(new Date())>0)) {
					
				//active or pending -> cannot delete
					if(b.getStatus()==1 || b.getStatus()==2) {
				
							c++;
							break;
						}
			
			}
		}
		

		// If no past booking for this room, then delete the room
		if(c==0) {
		// If no booking for this room, then delete the room
		
			adminService.deleteRoomById(id);
			return new ResponseEntity<String>("",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Room cannot be deleted as it has bookings present !!!",HttpStatus.OK);
		}
	}
	}
