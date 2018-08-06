package com.visa.prj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.prj.dao.RoomDao;
import com.visa.prj.entity.Room;

@Service
public class ClientService {

	@Autowired
	private RoomDao roomdao;
	
	public Room getRoomById(int id) {
		return roomdao.findById(id).get();
	}
	public List<Room> getRooms(){
		return roomdao.findAll();
	}
	
}
