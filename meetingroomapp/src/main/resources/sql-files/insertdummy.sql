use meeting_room_app;

insert into rooms(capacity,full_day_price,half_day_price,hour_price,name,status,description,img_src) values(10,1000,600,200,'Large Conference room',1,'This room is suitable for a large gathering. Contains all necessary amenities.','imgs/rooms/large.jpg');
insert into rooms(capacity,full_day_price,half_day_price,hour_price,name,status,description,img_src) values(15,2000,1200,400,'Medium Conference Room',1,'Originally meant for team meetings, this room can also be used for large gatherings.','imgs/rooms/medium.jpg');
insert into rooms(capacity,full_day_price,half_day_price,hour_price,name,status,description,img_src) values(20,3000,1800,600,'Small Conference Room',1, 'This is meant to be used for personal meetings.','imgs/rooms/small.jpg');

insert into layouts (name,img_src) values('Banquet Rounds','imgs/layouts/round.png');
insert into layouts (name,img_src) values('Classroom','imgs/layouts/horizontal.png');
insert into layouts (name,img_src) values('Conference','imgs/layouts/vertical.png');

insert into equipments (name, price, quantity) values ('Projector',100,100);
insert into equipments (name, price, quantity) values ('Mic',200,150);
insert into equipments (name, price, quantity) values ('Speaker',300,200);

insert into admins (email,name,password,status,registration_date) values ('shubhrit@gmail.com','shubhrit','shubhrit',1,'2018-08-07 07:34:09');
insert into admins (email,name,password,status,registration_date) values ('abhishek@gmail.com','abhishek','abhishek',1,'2018-07-07 05:21:54');

insert into clients (email,address,name,phone_number) values ('tanya@visa.com','Lucknow','Tanya Equipment',999999);
insert into clients (email,address,name,phone_number) values ('mansi@visa.com','Mumbai','Mansi Entity',777777);
insert into clients (email,address,name,phone_number) values ('ayush@visa.com','Kolkata','Ayush Master',888888);

insert into bookings (booking_date,from_date,status,to_date,type,client_fk,layout_fk,room_fk,amount) values ('2018-08-15 07:34:09','2018-08-1 10:00:00',3,'2018-08-15 13:00:00',1,1,1,1,1000);
insert into bookings (booking_date,from_date,status,to_date,type,client_fk,layout_fk,room_fk,amount) values ('2018-08-06 19:59:09','2018-08-07 15:00:00',1,'2018-08-07 18:00:00',1,3,2,2,15000);
insert into bookings (booking_date,from_date,status,to_date,type,client_fk,layout_fk,room_fk,amount) values ('2018-08-16 07:39:09','2018-08-17 10:00:00',2,'2018-08-17 14:00:00',2,2,3,3,20000);

insert into equipment_line_items (amount,quantity,equipment_fk,booking_fk) values (200,2,1,1);
insert into equipment_line_items (amount,quantity,equipment_fk,booking_fk) values (200,1,2,1);
insert into equipment_line_items (amount,quantity,equipment_fk,booking_fk) values (200,2,1,2);
insert into equipment_line_items (amount,quantity,equipment_fk,booking_fk) values (300,1,3,2);
insert into equipment_line_items (amount,quantity,equipment_fk,booking_fk) values (900,3,3,3);


