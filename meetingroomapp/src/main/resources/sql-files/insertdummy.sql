use meeting_room_app;

insert into rooms(capacity,full_day_price,half_day_price,hour_price,name,status) values(10,1000,600,200,'room1',1);
insert into rooms(capacity,full_day_price,half_day_price,hour_price,name,status) values(15,2000,1200,400,'room1',1);
insert into rooms(capacity,full_day_price,half_day_price,hour_price,name,status) values(20,3000,1800,600,'room1',1);

insert into layouts (name) values('layout1');
insert into layouts (name) values('layout2');
insert into layouts (name) values('layout3');

insert into equipments (name, price, quantity) values ('equip1',100,100);
insert into equipments (name, price, quantity) values ('equip2',200,150);
insert into equipments (name, price, quantity) values ('equip3',300,200);

insert into admins (email,name,password,status) values ('shubhrit@gmail.com','shubhrit','shubhrit',1);
insert into admins (email,name,password,status) values ('abhishek@gmail.com','abhishek','abhishek',1);

insert into clients (email,address,name,phone_number) values ('tanya@visa.com','lucknow','tanya',99999);
insert into clients (email,address,name,phone_number) values ('mansi@visa.com','mumbai','mansi',77777);
insert into clients (email,address,name,phone_number) values ('ayush@visa.com','kolkata','ayush',88888);

insert into bookings (booking_date,from_date,status,to_date,type,client_fk,layout_fk,room_fk) values ('2018-08-07 07:34:09','2018-08-07 10:00:00',1,'2018-08-07 13:00:00',1,'tanya@visa.com',1,1);
insert into bookings (booking_date,from_date,status,to_date,type,client_fk,layout_fk,room_fk) values ('2018-08-06 19:59:09','2018-08-07 15:00:00',1,'2018-08-07 18:00:00',1,'tanya@visa.com',1,2);
insert into bookings (booking_date,from_date,status,to_date,type,client_fk,layout_fk,room_fk) values ('2018-08-07 07:39:09','2018-08-08 10:00:00',2,'2018-08-07 14:00:00',2,'ayush@visa.com',2,1);

insert into equipment_line_items (amount,quantity,equipment_fk,booking_fk) values (200,2,1,1);
insert into equipment_line_items (amount,quantity,equipment_fk,booking_fk) values (200,1,2,1);
insert into equipment_line_items (amount,quantity,equipment_fk,booking_fk) values (200,2,1,2);
insert into equipment_line_items (amount,quantity,equipment_fk,booking_fk) values (300,1,3,2);
insert into equipment_line_items (amount,quantity,equipment_fk,booking_fk) values (900,3,3,3);


