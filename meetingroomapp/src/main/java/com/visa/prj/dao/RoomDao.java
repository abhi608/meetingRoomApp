package com.visa.prj.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.visa.prj.entity.Room;

public interface RoomDao extends JpaRepository<Room,Integer>{

	
	@Query("from Room r Order By r.capacity")
	public List<Room> getSortedRoom();

	@Query("from Room r where r.status=1")
	public List<Room> getActiveRooms();
}
