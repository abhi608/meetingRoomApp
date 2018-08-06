package com.visa.prj.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visa.prj.entity.Admin;
import com.visa.prj.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping("authadmin.do")
	public String getLoginDetails(HttpServletRequest req) {
		String email=req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println(email + password);
		//System.out.println(adminService.getAdminById(email));
		Admin admin = adminService.getAdminById(email); 
		
		if(admin != null) {
		if(admin.getPassword().equals(password) && admin.isStatus()) {
			return "redirect:admin.html?msg=login_success";
		}
		else 
			return "redirect:adminlogin.html?msg=try_again";
		}
		else
			return "redirect:adminlogin.html?msg=try_again";
		
	}

}
