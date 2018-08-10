package com.visa.prj.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.visa.prj.entity.Equipment;
import com.visa.prj.service.AdminService;

@RestController
public class EquipmentController {
	
	@Autowired
	private AdminService adminService;
	
	
	@RequestMapping(value="api/equipments",method=RequestMethod.GET)
	public @ResponseBody List<Equipment> getEquipments() {
		return adminService.getEquipments();
	}
	
	
	@RequestMapping(value="api/equipment",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Equipment> addEquipment(@RequestBody Equipment equipment) {
		adminService.addEquipment(equipment);
		return new ResponseEntity<Equipment>(equipment,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="api/equipment/{eq_id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteEquipment(@PathVariable("eq_id") int eq_id) {
		adminService.deleteEquipment(eq_id);
		return new ResponseEntity<String>("Product with id " + eq_id + " deleted !!!",HttpStatus.OK);
	}
	

}
