package com.visa.prj.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.visa.prj.view.AddBooking;



import com.visa.prj.entity.Booking;
import com.visa.prj.entity.Client;
import com.visa.prj.entity.Equipment;
import com.visa.prj.entity.EquipmentLineItem;
import com.visa.prj.entity.Layout;
import com.visa.prj.entity.Room;
import com.visa.prj.service.AdminService;

@RestController
public class BookingController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="api/bookings",method=RequestMethod.GET)
	public @ResponseBody List<Booking> getBookings() {
		System.out.println(adminService.getAllBookings());
		return adminService.getAllBookings();
	}
	
	@RequestMapping(value="api/room",method=RequestMethod.GET)
	public @ResponseBody List<Room> getRooms(){
		return adminService.getRooms();
	}
	
	@RequestMapping(value="api/layout",method=RequestMethod.GET)
	public @ResponseBody List<Layout> getLayouts(){
		return adminService.getLayouts();
	}
	
	@RequestMapping(value="api/makePending/{id}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateBooking1(@PathVariable("id") int id) {
		adminService.makePending(id);
		return new ResponseEntity<String>("Booking with id " + id + " updated !!!",HttpStatus.OK);
	}
	@RequestMapping(value="api/makeConfirmed/{id}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateBooking2(@PathVariable("id") int id) {
		adminService.makeConfirmed(id);
		return new ResponseEntity<String>("Booking with id " + id + " updated !!!",HttpStatus.OK);
	}
	@RequestMapping(value="api/makeCancelled/{id}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateBooking3(@PathVariable("id") int id) {
		adminService.makeCancelled(id);
		return new ResponseEntity<String>("Booking with id " + id + " updated !!!",HttpStatus.OK);
	}
	
	@RequestMapping(value="api/addbook",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addBooking(@RequestBody AddBooking addBooking) {
		//adminService.addNewBooking(addBooking);
		
		System.out.println(addBooking.toString());
		
		/************addBookings***************/
		Booking booking = new Booking();
		//hourly=1
		//halfday=2 //fullday=3
		
		double amount=0;
		
		Date fromDate;
		Date toDate;
		int type = Integer.parseInt(addBooking.getType());
		booking.setType(type);
		booking.setBookingDate(new Date());
		booking.setStatus(2);
		String roomId= addBooking.getRoom();
		String layoutId = addBooking.getLayout();
		
		Room room = adminService.getRoomById(Integer.parseInt(roomId));
		Layout layout = adminService.getLayoutById(Integer.parseInt(layoutId));
		
		booking.setRoom(room);
		booking.setLayout(layout);
		
		
		String justDate = addBooking.getTbdate();
		String fromTime = null;
		String toTime = null;
		if(type==1) {
			fromTime = addBooking.getFromtime();
			toTime = addBooking.getTotime();
			
		}else if (type==2) {
			String slot= addBooking.getSlot();
			if(slot.equals("morning")){
				fromTime="09:00";
				toTime="13:00";
			}else if(slot.equals("afternoon")){
				fromTime="14:00";
				toTime="18:00";
			}
			amount=amount+room.getHalfDayPrice();
		}else {
			fromTime="09:00";
			toTime="18:00";
			amount=amount+room.getFullDayPrice();
		}
		
		
		
		String fromDatestr = justDate +" "+ fromTime;
		String toDatestr = justDate +" "+ toTime;
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
		try {
			fromDate = format.parse(fromDatestr);
			toDate = format.parse(toDatestr);
			booking.setFromDate(fromDate);
			booking.setToDate(toDate);
			long secs = (toDate.getTime() - fromDate.getTime()) / 1000;
			int hours =  (int) Math.ceil(secs/3600);
			if(type==1) {
				amount=amount+hours*room.getHourPrice();
			}
			
			System.out.println(hours);
			System.out.println(toDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		
		Client client = new Client();
		client.setAddress(addBooking.getUseraddress());
		client.setEmail(addBooking.getUseremail());
		client.setName(addBooking.getUsername());
		client.setPhoneNumber(Integer.parseInt(addBooking.getUserphone()));
		
		booking.setClient(client);
		
		
		
		/*******equipments addition************/
		List<String> equipIds=addBooking.getEquipmentIds();
		List<String> equipQty=addBooking.getEquipmentQty();
		List<EquipmentLineItem> equipmentLineItems = new ArrayList<>();
		for(int i=0; i< equipIds.size();i++) {
			EquipmentLineItem e =new EquipmentLineItem();
			Equipment eq = adminService.getEquipmentById(Integer.parseInt(equipIds.get(i)));
			
			eq.setQuantity(eq.getQuantity()-Integer.parseInt(equipQty.get(i)));
			
			amount= amount + Integer.parseInt(equipQty.get(i))*(eq.getPrice());
			e.setEquipment(eq);
			e.setQuantity(Integer.parseInt(equipQty.get(i)));
			e.setAmount(Integer.parseInt(equipQty.get(i))*(eq.getPrice()));
			equipmentLineItems.add(e);
			
		}
		
		booking.setAmount(amount);
		booking.setEquipLineItem(equipmentLineItems);
		adminService.addBooking(booking);
		
		
		return new ResponseEntity<String>("SUCCESS==TRUE",HttpStatus.CREATED);
	}
	
}
	