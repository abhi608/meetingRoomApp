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

import com.visa.prj.entity.Room;
import com.visa.prj.service.AdminService;


@RestController
public class MeetingRoomController {

	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="api/getMeetingRooms",method=RequestMethod.GET)
	public @ResponseBody List<Room> getRooms() {
		return adminService.getRooms();
	}
	
	
	@RequestMapping(value="api/changeRoomStatus/{id}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateRoom(@PathVariable("id") Integer id) {
		adminService.changeRoomStatus(id);
		return new ResponseEntity<String>("Room with id " + id + " updated !!!",HttpStatus.OK);
	}
	
	@RequestMapping(value="api/room/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteRoom(@PathVariable("id") int id) {
		adminService.deleteRoomById(id);
		return new ResponseEntity<String>("Admin with id " + id + " deleted !!!",HttpStatus.OK);
	}
}
