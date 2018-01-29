window.setInterval(function() {
	var d = new Date();
	var yyyy = d.getFullYear();
	var MM = d.getMonth() + 1;
	var dd = d.getDate();
	var HH = d.getHours();
	var mm = d.getMinutes();
	var ss = d.getSeconds();
	var week = d.getDay();
	switch(week) {
		case 0:
			week = "Sun";
			break;
		case 1:
			week = "Mon";
			break;
		case 2:
			week = "Tue";
			break;
		case 3:
			week = "Wed";
			break;
		case 4:
			week = "Thur";
			break;
		case 5:
			week = "Fri";
			break;
		case 6:
			week = "Sat";
			break;
	}
	document.getElementById("time").value = yyyy + "-" + MM + "-" + dd + " " + HH + ":" + mm + ":" + ss + "   " + week;
}, 1000);