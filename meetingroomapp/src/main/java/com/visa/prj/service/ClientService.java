package com.visa.prj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.prj.dao.EquipmentDao;
import com.visa.prj.dao.LayoutDao;
import com.visa.prj.dao.RoomDao;
import com.visa.prj.entity.Equipment;
import com.visa.prj.entity.Layout;
import com.visa.prj.entity.Room;

@Service
public class ClientService {

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private LayoutDao layoutDao;
	
	@Autowired
	private EquipmentDao equipmentDao;
	
	public Room getRoomById(int id) {
		return roomDao.findById(id).get();
	}
	public List<Room> getRooms(){
		return roomDao.findAll();
	}
	
	public List<Room> getSortedRooms(){
		return roomDao.getSortedRoom();
	}
	public List<Layout> getLayouts(){
		return layoutDao.findAll();
	}
	public List<Equipment> getEquipments(){
		return equipmentDao.findAll();
	}
	
}
