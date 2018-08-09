package com.visa.prj.service;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.prj.dao.AdminDao;
import com.visa.prj.dao.BookingDao;
import com.visa.prj.dao.ClientDao;
import com.visa.prj.dao.EquipmentDao;
import com.visa.prj.dao.LayoutDao;
import com.visa.prj.dao.RoomDao;
import com.visa.prj.entity.Admin;
import com.visa.prj.entity.Booking;
import com.visa.prj.entity.Client;
import com.visa.prj.entity.Equipment;
import com.visa.prj.entity.Layout;
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
	
	@Autowired
	private LayoutDao layoutDao;
	
	public Admin getAdminById(String email) {
		try {
			return adminDao.findById(email).get();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Admin> getAllAdmins(){
		try {
			return adminDao.findAll();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Transactional
	public void deleteAdminByEmail(String email) {
		try {
			adminDao.deleteById(email);
		} catch (Exception e) {
			System.out.println("no admin to delete!");
		}
	}
	
	public List<Booking> getAllBookings() {
		try {
			return bookingDao.findAll();
		}catch(Exception e) {
			return null;
		}
	}
		
	@Transactional
	public void addBooking(Booking booking) {
		bookingDao.save(booking);
	}
	
	@Transactional
	public void deleteBookingById(Booking booking) {
		try {
				Booking b = bookingDao.findById(booking.getId()).get();
				bookingDao.delete(b);
			}catch(Exception e) {
				System.out.println("No booking to delete");
			}
	}
	
	@Transactional
	public List<Booking > getSortedBookings() {
	
		return bookingDao.getSortedBooking();
	}
	
	
	//gives the total number of booking on that day
	public int getBookingCountByDate() {
		
		return 0;
		
		
	}
	
	public void updateBookingIdById(int id, Booking bk) {
		Booking b = bookingDao.getOne(id);
		b.setRoom(bk.getRoom());
		b.setLayout(bk.getLayout());
		b.setType(bk.getType());
		b.setStatus(bk.getStatus());
		b.setEquipLineItem(bk.getEquipLineItem());
	}

	public Client getClientById(String email) {
		return clientDao.findById(email).get();
	}
	
	
	public List<Room> getRooms() {
		try {
		return roomDao.findAll();
	}catch(Exception e) {
		return null;
	}
	}
	
	@Transactional
	public void addRoom(Room room) {
		roomDao.save(room);
	}
	

	public Room getRoomById(int id) {
		try {
		return roomDao.findById(id).get();
		}catch(Exception e) {
			return null;
		}
	}
		
	
	public List<Equipment> getEquipments() {
		try{
		return equipmentDao.findAll();
	}catch(Exception e) {
		return null;
	}
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
	public List<Layout> getLayouts() {
		try {
			return layoutDao.findAll();
		}catch(Exception e) {
			return null;
		}
	}
	
	
	public boolean authAdmin(String email, String password) {
		
		return false;
	}
	
	public List<Booking> getBookingByDate(Date date){
		try {
			return bookingDao.getBookingByDate(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public List<Booking> getBookingMadeByDate(Date date){
		try {
			return bookingDao.getBookingMadeByDate(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Transactional
	public void changeAdminStatus(String email) {
		Admin a = adminDao.getOne(email);
		try {
			a.setStatus(!a.isStatus());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<Room> getActiveRooms() {
		// TODO Auto-generated method stub
		try {
			return roomDao.getActiveRooms();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@Transactional
	public void makePending(int id) {
		Booking b = bookingDao.getOne(id);
		
		try {
			b.setStatus(2);
		} catch (Exception e) {
			
		}
				
	}
	
	@Transactional
	public void makeConfirmed(int id) {
		Booking b = bookingDao.getOne(id);
		
		try {
			b.setStatus(1);
		} catch (Exception e) {
			
		}
				
	}
	
	@Transactional
	public void makeCancelled(int id) {
		Booking b = bookingDao.getOne(id);
		
		try {
			b.setStatus(3);
		} catch (Exception e) {
			
		}
	}
				

	
	@Transactional
	public void changeRoomStatus(Integer room_id) {
		Room a = roomDao.getOne(room_id);
		try {
			a.setStatus(!a.isStatus());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deleteRoomById(int id) {
		// TODO Auto-generated method stub
		try {
			
			//List<Booking > bk= bookingDao.getBookingByRoom(id);
			
			//System.out.println(bk);
			// If no booking for this room, then delete the room
			//if(bk==null) {
				roomDao.deleteById(id);
			//}
			//else {
				//JOptionPane.showMessageDialog(null, "Room cannot be deleted as there are bookings associated with this room!!");
			//}
			
		} catch (Exception e) {
			System.out.println("error aaya tha");
		}
		
	}
	
	
	
	
	
	
}