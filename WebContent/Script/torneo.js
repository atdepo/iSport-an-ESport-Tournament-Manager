$(document).ready(function(){
	 	var xhr = new XMLHttpRequest();
		var nomeT=$('.nomeT');
		var nomeS=$('.nomeS');
		var sImage=$('.loghi');
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == 4) {
				let data = JSON.parse(xhr.responseText);
				console.log(data);			
				nomeT.append('<div class=nomeT><h2>'+data[1][0]+' </h2></div> ');
				nomeS.append('<div class=nomeS><h3>Questo torneo si svolgerà nella struttura '+data[1][1]+' </h3></div> ');
				
				for (var i = 0; i < data[0].length; i++) {
					var nome=data[0][i].nome;
					sImage.append('<div><h5>'+data[0][i].nome+'</h5><div class="sImage '+nome+'" > <a href="UserControl?action=visualizzaSquadra&nomeSquadra='+ nome +'"> <img src='+data[0][i].teamImage+'></img></a> </div></div>');
				}
			}
		

		}
		xhr.open('GET', 'UserControl?action=getSquadreFromTorneo&codTorneo='+$('#torneo').val(), true);	
		xhr.send();
 })

