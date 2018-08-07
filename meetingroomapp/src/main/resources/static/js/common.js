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

function openBookings(evt, cityName){
	var bksTempl$;
	$.get("templates/booking.html",function(templ) {
		bksTempl$ = templ;
		var bookings$ = $("#bookings");
		$.getJSON("http://localhost:8080/api/bookings", function(books) {
			 
			var content = Mustache.render(bksTempl$, books);
			$('#ModuleUserTable').append(content);
			display(evt, cityName);
		
		});
	 });
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