	$(document).ready(function(){
	
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			
			if (xhr.status == 200 && xhr.readyState == 4) {
				let data = JSON.parse(xhr.responseText);
				console.log(data);			
				var div=$(".listaTornei");
				for (var i = 0; i < data.length; i++) {
					div.append('<p>Nome Torneo= '+data[i].nome+' <a href="../TournamentControl?action=deleteTorneo&cod='+data[i].codice+'"><i class="fas fa-dumpster-fire"></i></a><br>');	
		
				}
			
			}
		

	}
	xhr.open('GET', '../TournamentControl?action=getTornei', true);		
	xhr.send();
		})