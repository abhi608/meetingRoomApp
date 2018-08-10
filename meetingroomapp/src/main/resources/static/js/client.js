function getUserRoomSetup(){
	var layout$=$("#layout");
	var template1 = "templates/userLayout.html";
	var equipment$ =$("#equipment");
	var template2=  "templates/userEquipment.html";
	$.get(template1,function(temp1){
		var temp1$=temp1;
		$.get(template2,function(temp2){
			var temp2$=temp2;
			$.getJSON("http://localhost:8080/api/layout", function(data1){
				var content1=Mustache.render(temp1$, data1);
				layout$.html(content1);
				
				})
			$.getJSON("http://localhost:8080/api/equipments", function(data2){
					var content2=Mustache.render(temp2$, data2);
					equipment$.html(content2);
				
			})
			
		})
	})
}

$(function() {
    console.log( "ready!", location.search);
    var query = location.search;
    query = query.split("&");
    if(query[0] == "?step1"){
    	var template1 = "templates/clientStep1.html";
	    var rooms$ = $("#step"); 
	    $.get(template1, function(temp1){
	    	var temp1$ = temp1;
	    	$.get("http://localhost:8080/api/getActiveRooms", function(rooms){
	    		var content1 = Mustache.render(temp1$, rooms);
				rooms$.html(content1);
		    });
	    });
    } else if(query[0] == "?step2"){
    	console.log("Inside step2");
    	var template1 = "templates/clientStep2.html";
	    var rooms$ = $("#step"); 
	    var roomName = decodeURIComponent(query[1]);
	    $.get(template1, function(temp1){
	    	var temp1$ = temp1;
	    	var content1 = Mustache.render(temp1$, {"roomName": roomName});
			rooms$.html(content1);
	    });
    } else if(query[0] == "?step3"){
    	console.log("Inside Step3");
    	var template1 = "templates/clientStep3.html";
    	var template2 = "templates/bookequipments.html";
    	var template3 = "templates/booklayout.html"
	    var rooms$ = $("#step"); 
	    $.get(template1, function(temp1){
	    	var temp1$ = temp1;
	    	var content1 = Mustache.render(temp1$, {});
			rooms$.html(content1);
	    	$.get(template2, function(temp2){
	    		var temp2$ = temp2;
	    		$.get(template3, function(temp3){
	    			var temp3$ = temp3;
	    			$.getJSON("http://localhost:8080/api/equipments", function(equipments){
			    		var content2 = Mustache.render(temp2$, equipments);
						$('#bookEquimentTable').empty();
						$('#bookEquimentTable').append(content2);
						$.getJSON("http://localhost:8080/api/layout", function(layouts){
							var content3 = Mustache.render(temp3$, layouts);
							$('#layout').empty();
							$('#layout').append(content3);
						});	
			    	});
	    		});
	    		
				
	    	});
	    });
    } else if(query[0] == "?step4"){
    	console.log("Inside Step4");
    	var template1 = "templates/clientStep4.html";
	    var rooms$ = $("#step");
	    $.get(template1, function(temp1){
	    	var temp1$ = temp1;
	    	var content1 = Mustache.render(temp1$, {});
			rooms$.html(content1);
	    });
    }
    
});

function submitStep1(step, roomId, roomName){
	console.log("inside submit-1");
	console.log(step, roomId);
	var url = "http://localhost:8080/api/step/" + step.toString();
	$.ajax({
		url: url,
		type: "POST",
		data: JSON.stringify({"roomId": roomId}),
		contentType:"application/json",
		success: function(data){
			console.log("data: ", data);
			window.location.href = "http://localhost:8080/clientLanding.html?step2&" + roomName;
		}
	});
}

function submitStep2(step){
	console.log("inside submit-2");
	var addBooking = {};
	addBooking["tbdate"]=$("#tbdate").val();
	addBooking["type"]=$("#type").val();
	addBooking["slot"]=$("#slot").val();
	addBooking["fromtime"]=$("#fromtime").val();
	addBooking["totime"]=$("#totime").val();
	var url = "http://localhost:8080/api/step/" + step.toString();
	$.ajax({
		url: url,
		type: "POST",
		data: JSON.stringify(addBooking),
		contentType:"application/json",
		success: function(msg){
			console.log("msg: ", msg);
			window.location.href = "http://localhost:8080/clientLanding.html?step3";
		}
	});
}

function submitStep3(step){
	console.log("inside submit-3");
	var addBooking = {};
	var eqipIds=[];
	var equipqty=[];
	addBooking["layout"]=$("#layout").val();
	$('.equips').each(function(){
        if($(this).is(':checked'))
        {
            console.log("hua");
            var equipId = $(this).attr('VALUE');
            eqipIds.push(equipId);
            
            var id = "#equipqty_" + equipId;
            console.log($(id));
            var id$ = $(id).val();
            equipqty.push(id$);
        	//addBooking.push({value:$(this).attr('VALUE')}); 
        }        
    });
	addBooking["equipmentIds"]=eqipIds;
	addBooking["equipmentQty"]=equipqty;
	var url = "http://localhost:8080/api/step/" + step.toString();
	$.ajax({
		url: url,
		type: "POST",
		data: JSON.stringify(addBooking),
		contentType:"application/json",
		success: function(msg){
			console.log("msg: ", msg);
			window.location.href = "http://localhost:8080/clientLanding.html?step4";	
		}
	});
}

function submitStep4(step){
	console.log("inside submit-4");
	var addBooking = {};
	addBooking["username"]=$("#username").val();
	addBooking["useremail"]=$("#useremail").val();
	addBooking["userphone"]=$("#userphone").val();
	addBooking["useraddress"]=$("#useraddress").val();
	var url = "http://localhost:8080/api/final";
	$.ajax({
		url: url,
		headers: {          
	    	Accept: "application/json" 
	    },
		type: "POST",
		data: JSON.stringify(addBooking),
		contentType:"application/json",
		success: function(addBooking){
			console.log("msg: ", addBooking);
			$.ajax({
				url: "http://localhost:8080/api/addbook",
				type: "POST",
				data: JSON.stringify(addBooking),
				contentType:"application/json",
				success: function(data){
					console.log("data: ", data);
					alert("Booking added Successfully! You'll receive a confirmation mail shortly.");
					window.location.href = "http://localhost:8080/clientLanding.html?step1";
				}
			});
		}
	});
}