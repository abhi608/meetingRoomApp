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