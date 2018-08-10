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

import com.visa.prj.entity.Admin;
import com.visa.prj.service.AdminService;

@RestController
public class UserController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="api/admins",method=RequestMethod.GET)
	public @ResponseBody List<Admin> getAdmins() {
		return adminService.getAllAdmins();
	}
	
	@RequestMapping(value="api/admin/{email}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteAdmin(@PathVariable("email") String email) {
		adminService.deleteAdminByEmail(email);
		return new ResponseEntity<String>("Admin with id " + email + " deleted !!!",HttpStatus.OK);
	}
	
	@RequestMapping(value="api/changeAdminStatus/{email}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateAdmin(@PathVariable("email") String email) {
		adminService.changeAdminStatus(email);
		return new ResponseEntity<String>("Admin with id " + email + " updated !!!",HttpStatus.OK);
	}
}
