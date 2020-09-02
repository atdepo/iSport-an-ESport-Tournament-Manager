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
			if(data.length==0)
				div.append("<h3>Non hai tornei, dai ordina il tuo primo torneo dalla sezione Crea Tornei</h3>");
			for (var i = 0; i < data.length; i++) {
				div.append('<div class="torneo" onclick="getSquadra('+data[0][i].codice+')">  <h3> '+data[0][i].nome+'</h3><h3> '+data[0][i].data+'</h3><h3> '+data[1][i]+'</h3><h3> '+data[0][i].indirizzoStruttura+'</h3><h3> '+data[0][i].codGioco+'</h3><br></div>');	
	
			}
		
		}
	

	}
	xhr.open('GET', '../UserControl?action=getSquadreFromTorneo(codice)', true);	
	xhr.send();
 
 }