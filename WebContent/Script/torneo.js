$(document).ready(function(){
	 	var xhr = new XMLHttpRequest();
		var nomeT=$('.nomeT');
		var nomeS=$('.nomeS');
		var squadre=$('.nomiTeam');
		var sImage=$('.loghi');
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == 4) {
				let data = JSON.parse(xhr.responseText);
				console.log(data);			
				nomeT.append('<div class=nomeT><h2>'+data[1][0]+' </h2></div> ');
				nomeS.append('<div class=nomeS><h3>Struttura='+data[1][1]+' </h3></div> ');
				
				for (var i = 0; i < data[0].length; i++) {
					squadre.append('<div class=squadre>'+data[0][i].nome +' </div> ');	
				}
				for (var i = 0; i < data[0].length; i++) {
					sImage.append('<div class=sImage> <img src='+data[0][i].teamImage+'></img> </div> ');
				}
			}
		

		}
		xhr.open('GET', 'UserControl?action=getSquadreFromTorneo&codTorneo='+$('#torneo').val(), true);	
		xhr.send();
 })
