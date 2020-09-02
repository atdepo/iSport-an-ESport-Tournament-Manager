/**
 * 
 */
 
 $(document).ready(function(){
 	var xhr = new XMLHttpRequest();
	xhr.open('GET', '../UserControl?action=init', true);	
	xhr.send();
 
 })
 
 function getSquadre(codice){
 	var xhr = new XMLHttpRequest();
	var div=$('.test');
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);			
			for (var i = 0; i < data.length; i++) {
				
			}
		
		}
	

	}
	xhr.open('GET', '../UserControl?action=getSquadreFromTorneo(codice)', true);	
	xhr.send();
 
 }