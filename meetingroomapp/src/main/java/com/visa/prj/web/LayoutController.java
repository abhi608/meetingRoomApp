package com.visa.prj.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.visa.prj.entity.Layout;
import com.visa.prj.service.AdminService;

public class LayoutController {
	
	@Autowired
	private AdminService adminService;
	@RequestMapping(value="api/getLayouts",method=RequestMethod.GET)
	public @ResponseBody List<Layout> getLayouts() {
		return adminService.getLayouts();
	}

}
