package com.visa.prj.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="api/getActiveRooms",method=RequestMethod.GET)
	public @ResponseBody List<Room> getActiveRooms() {
		return adminService.getActiveRooms();
	}
}
