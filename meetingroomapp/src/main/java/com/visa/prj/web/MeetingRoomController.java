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
	public ResponseEntity<String> updateRoom(@PathVariable("id") Integer room_id) {
		adminService.changeRoomStatus(room_id);
		return new ResponseEntity<String>("Room with id " + room_id + " updated !!!",HttpStatus.OK);
	}
}
