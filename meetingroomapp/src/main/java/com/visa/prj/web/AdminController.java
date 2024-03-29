package com.visa.prj.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
				HttpSession ses = req.getSession();
				ses.setAttribute("user", email);
				return "redirect:commonView.html?msg=login_success";
			} else if(!admin.getPassword().equals(password)) {
				return "redirect:adminLoginView.html?msg=Invalid Email/password";
			} else {
				return "redirect:adminLoginView.html?msg=User Inactive";
			}
		} catch (Exception e) {
			return "redirect:adminLoginView.html?msg=Invalid Email/password";
		}
	
	}
	
	
	
	

}
