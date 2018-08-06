package com.visa.prj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.prj.dao.AdminDao;
import com.visa.prj.dao.BookingDao;
import com.visa.prj.dao.ClientDao;
import com.visa.prj.dao.EquipmentDao;
import com.visa.prj.dao.RoomDao;
import com.visa.prj.entity.Admin;
import com.visa.prj.entity.Booking;
import com.visa.prj.entity.Client;
import com.visa.prj.entity.Equipment;
import com.visa.prj.entity.Room;



@Service
public class AdminService {

	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private EquipmentDao equipmentDao;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private AdminDao adminDao;
	
	
	
	public Admin getAdminById(String email) {
		return adminDao.findById(email).get();
	}
	
	public List<Booking> getBookings() {
		return bookingDao.findAll();
	}
	
	@Transactional
	public void addBooking(Booking booking) {
		bookingDao.save(booking);
	}
	
	@Transactional
	public void deleteBooking(Booking booking) {
		Booking b = bookingDao.findById(booking.getId()).get();
		bookingDao.delete(b);
	}

	public Client getClientById(String email) {
		return clientDao.findById(email).get();
	}
	
	
	public List<Room> getRooms() {
		return roomDao.findAll();
	}
	
	@Transactional
	public void addRoom(Room room) {
		roomDao.save(room);
	}
	

	public Room getRoomById(int id) {
		return roomDao.findById(id).get();
	}
		
	
	public List<Equipment> getEquipments() {
		return equipmentDao.findAll();
	}
	
	
	
	@Transactional
	public void addEquipment(Equipment p ) {
		equipmentDao.save(p);
	}
	
	
	
	@Transactional
	public void deleteEquipment(int eq_id) {
		Equipment e = equipmentDao.findById(eq_id).get();
		equipmentDao.delete(e);
	}
	
	@Transactional
	public void updateEquipment(int id, Equipment eq) {
		Equipment e = equipmentDao.getOne(id);
		e.setName(eq.getName());
		e.setPrice(eq.getPrice());
		e.setQuantity(eq.getQuantity());
	}
	
	
	public boolean authAdmin(String email, String password) {
		
		return false;
	}
	
	
	
	
	
	
	
}