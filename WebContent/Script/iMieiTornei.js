	$(document).ready(function(){
	
		var xhr = new XMLHttpRequest();
		$(".mimmo").append(xhr.readyState);
		xhr.onreadystatechange = function() {
			
			if (xhr.status == 200 && xhr.readyState == 4) {
				let data = JSON.parse(xhr.responseText);
				console.log(data);			
				var div=$(".listaTornei");
				for (var i = 0; i < data.length; i++) {
					div.append("MAMMT");	
		
				}
			
			}
		

	}
	xhr.open('GET', '../TournamentControl?action=getMieiTornei', true);		
	xhr.send();
		})