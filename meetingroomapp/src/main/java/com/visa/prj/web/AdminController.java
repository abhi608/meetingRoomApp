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
		try {
			Admin admin = adminService.getAdminById(email);
			if(admin.getPassword().equals(password) && admin.isStatus()) {
				return "redirect:admin.html?msg=login_success";
			} else if(!admin.getPassword().equals(password)) {
				return "redirect:adminlogin.html?msg=Invalid Email/password";
			} else {
				return "redirect:adminlogin.html?msg=User Inactive";
			}
		} catch (Exception e) {
			return "redirect:adminlogin.html?msg=Invalid Email/password";
		}
	
	}

}
