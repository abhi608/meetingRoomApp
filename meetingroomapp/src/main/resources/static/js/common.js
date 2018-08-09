function display(evt, cityName) {
	console.log("test: ", evt);
		var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
        	tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
        	tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(cityName).style.display = "block";
        if(cityName == "Dashboard"){
        	$("#defaultOpen").addClass("active");
        } else if(cityName == "Bookings"){
        	$("#bookingsTab").addClass("active");
        } else if(cityName == "BookRoom"){
        	$("#addBookingTab").addClass("active");
        } else if(cityName == "Users"){
        	$("#usersTab").addClass("active");
        }
        else if(cityName == "ShowRooms"){
        	$("#addRoomTab").addClass("active");
        }
        //evt.currentTarget.className += " active";
}

function openCity(evt, cityName) {
	var id = "#" + cityName;
	var id$ = $(id);
	var template = "templates/" + cityName + ".html";
	var templ$;
	console.log(id, id$, template);
	$.get(template, function(templ){
		templ$ = templ;
		if(cityName == "Dashboard"){
			$.getJSON("http://localhost:8080/api/dashboard", function(data){
                        	var content = Mustache.render(templ$, data);
                        	id$.html(content);
							display(evt, cityName); 
                	});
		}
	});
}

function openUsers(evt, cityName){
	var usersTempl$;
	$.get("templates/user.html",function(templ) {
		usersTempl$ = templ;
		$.getJSON("http://localhost:8080/api/admins", function(users) {
			for(var i=0; i<users.length; i++){
				if('status' in users[i]){
					if(users[i].status){
						users[i].status = "Active";
						
					} else{
						users[i].status = "Inactive";
					}
				}
			}
			var content = Mustache.render(usersTempl$, users);
			$("#userTable").empty();
			$('#userTable').html(content);
			display(evt, cityName);
		
		});
	 });
}
function openRooms(evt, cityName){
	var roomTempl$;
	$.get("templates/showRooms.html",function(templ) {
		roomTempl$ = templ;
		$.getJSON("http://localhost:8080/api/room", function(rooms) {
			for(var i=0; i<rooms.length; i++){
				if('status' in rooms[i]){
					if(rooms[i].status){
						rooms[i].status = "Active";
						
					} else{
						rooms[i].status = "Inactive";
					}
				}
			}
			var content = Mustache.render(roomTempl$, rooms);
			$("#RoomDisplayTable").empty();
			$('#RoomDisplayTable').html(content);
			display(evt, cityName);
		
		});
	 });
}

function deleteAdmin(email){
	var url = "/api/admin/" + email;
	$.ajax({
	    url: url,
	    type: 'DELETE',
	    success: function(message) {
	        openUsers(null, "Users");
	    }
	});
}

function changeAdminStatus(email){
	var url = "/api/changeAdminStatus/" + email;
	var data = {};
	console.log(data);
	$.ajax({
        type: 'PUT',
        url: url,
        data: JSON.stringify(data),
        contentType:"application/json",
        success: function (message) {
        	openUsers(null, "Users");
        }
    });
}
function changeRoomStatus(room_id){
	console.log("xnaskjcnas");
	var url = "/api/changeRoomStatus/" + room_id;
	var data = {};
	console.log(data);
	$.ajax({
        type: 'PUT',
        url: url,
        data: JSON.stringify(data),
        contentType:"application/json",
        success: function (message) {
        	openRooms(null, "" +
        			"");
        }
    });
}
function openBookings(evt, cityName){
	var bksTempl$;
	$.get("templates/booking.html",function(templ) {
		bksTempl$ = templ;
		$.getJSON("http://localhost:8080/api/bookings", function(books) {
			var content = Mustache.render(bksTempl$, books);
			$('#ModuleUserTable').append(content);
			display(evt, cityName);
		
		});
	 });
}

function addBooking(evt, cityName){
	console.log("evt, cityName: ", evt, cityName);
	var roomTempl$;
	var layoutTempl$;
	$.get("templates/layout.html",function(layouttempl) {
		layoutTempl$ = layouttempl;
		$.getJSON("http://localhost:8080/api/layout", function(layouts) {
			 
			var content = Mustache.render(layoutTempl$, layouts);
			$('#layout').append(content);
			
			$.get("templates/room.html",function(roomtempl) {
				roomTempl$ = roomtempl;
				$.getJSON("http://localhost:8080/api/room", function(rooms) {
					 
					content = Mustache.render(roomTempl$, rooms);
					$('#room').append(content);
					display(evt, cityName);
				
				});
			 });
		
		});
	 });
}

function typeCheck(that) {
    if (that.value == "halfday") {
        document.getElementById("halfday").style.display = "block";
    	document.getElementById("perhour").style.display = "none";
		
    }else if(that.value == "perhour"){
        document.getElementById("halfday").style.display = "none";
    	document.getElementById("perhour").style.display = "block";
    }else {
        document.getElementById("halfday").style.display = "none";
    	document.getElementById("perhour").style.display = "none";
    }
}

function openDashboard(evt, cityName){
	var dashboard1$ = $("#dashboard1");
	var template1 = "templates/dashboard1.html";
	var dashboard2$ = $("#dashboard2");
	var template2 = "templates/dashboard2.html";
	var dashboard3$ = $("#dashboard3");
	var template3 = "templates/dashboard3.html";
	$.get(template1, function(temp1){
		var temp1$ = temp1;
		$.get(template2, function(temp2){
			var temp2$ = temp2;
			$.get(template3, function(temp3){
				var temp3$ = temp3;
				$.getJSON("http://localhost:8080/api/dashboard", function(data1){
					var content1 = Mustache.render(temp1$, data1);
					dashboard1$.html(content1);
					var content2 = Mustache.render(temp2$, data1);
					dashboard2$.html(content2);
					var content3 = Mustache.render(temp3$, {});
					dashboard3$.html(content3);
					display(evt, cityName);
				});
			});
		});
	});
}

$(document).on("click","#reservationBtn", function(){
	var date = $("#reservationDate").val();
	if(!date){
		return;
	}
	var splitDate = date.split("-");
	var dashboard3$ = $("#dashboard3");
	var template3 = "templates/dashboard3.html";
	var formatDate = splitDate[2] + "/" + splitDate[1] + "/" + splitDate[0];
	console.log("formatDate: ", formatDate);
	$.get(template3, function(temp3){
		var temp3$ = temp3;
		$.ajax({
			url: "http://localhost:8080/api/dashboard",
			type: "POST",
			data: JSON.stringify({"date": formatDate}),
			contentType:"application/json",
			dataType:"json",
			success: function(data){
				console.log("data: ", data);
				dashboard3$.empty();
				var content =Mustache.render(temp3$, data);
				dashboard3$.html(content);
			}
		});
		
	})
	
});

document.getElementById("defaultOpen").click();