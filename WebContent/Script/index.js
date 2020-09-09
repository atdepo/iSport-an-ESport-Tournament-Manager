/**
 * allò cirù mo t facc nu template per il cazzafà dei tornei in home, tu faj o css e accuong nu poc comm sul tu saj fa<3
 */



$(document).ready(function() {
	var div=$("#ciruzz");
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);		
			
			for (var i = 0; i < data[0].length; i++) {
				div.append("cirù t'ho fatto i json, data[i].chellCVuò");
			}
		}
	

	}
	xhr.open('GET', 'IndexControl?action=TournamentHome', true);	
	xhr.send();
	
})