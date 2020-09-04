/**
 * 
 */
 
 $(document).ready(function(){
	 	var xhr = new XMLHttpRequest();
		//var datiP=$('.dati');
		//var pImage=$('.pImage');
		//var nomeS=$('.nomeS');
	 	var giocatori=$('.giocatori');
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == 4) {
				var data = JSON.parse(xhr.responseText);
				console.log(data);			
				
				for (var i = 0; i < data['0'].length; i++) {
					//nomeS.append('<div class=nomeS>'+data[i].nome +' </div> ');	
					//pImage.append('<div class=pImage> <img src='+data[i].teamImage+'></img> </div> ');
					//datiP.append('<div class=datiP>'+data[i].nome +' </div>');
					giocatori.append('<div><h5>'+data['0'][i]+'</h5><div class="'+data['0'][i]+'"><a onclick="mostraGiocatore(\''+data['0'][i]+'\') "><img src='+data['1'][i]+'></img></div></div> ');
				}
			}
		}
		xhr.open('GET', 'UserControl?action=getGiocatoriFromSquadra&nomeSquadra='+$('.teamName').val(), true);	
		xhr.send();
 })
 
 
 function mostraGiocatore(i){
	 var div=$('.dati');
	 var xhr = new XMLHttpRequest();
	
	 xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			var data = JSON.parse(xhr.responseText);
			console.log(data);	
			div.empty();
			div.append('<h3>DATI DEL GIOCATORE: '+data[0].nickname+'</h3><br>');
			div.append('<span>NOME: <small>'+data[0].nome+'</small><br><br>'+
						 'NICKNAME: <small>'+data[0].nickname+'</small><br><br>'+
						  'COGNOME: <small>'+data[0].cognome+'</small><br><br>'+
				  'DATA DI NASCITA: <small>'+data[0].datanascita+'</small><br><br>'+
			    			'RUOLO: <small>'+data[0].ruolo+'</small><br><br>'+
					   '</span>');
			
		}
	}
	 
	 xhr.open('GET', 'UserControl?action=getDatiGiocatore&nick='+i, true);	
	 xhr.send();
	 
 }
 
 
 
 
 