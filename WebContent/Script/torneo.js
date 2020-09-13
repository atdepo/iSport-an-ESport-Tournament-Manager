

$(document).ready(function(){
	 	var xhr = new XMLHttpRequest();
		//var nomeT=$('.nomeT');
		//var nomeS=$('.nomeS');
		var sImage=$('.loghi');
		var text=$(".problem");
		var email=$("#user");
		var codice=$("#torneo");
		var bellezza=new XMLHttpRequest();
		bellezza.onreadystatechange = function() {
			if (bellezza.status == 200 && bellezza.readyState == 4) {
				let data = JSON.parse(bellezza.responseText);
				console.log(data);			
		
				if(data)
					text.append("<textarea id='problem' placeholder='qualcosa'></textarea> <input type='button' value='invia' onclick='testo()'>");
			}
		}	
		bellezza.open('GET', 'UserControl?action=getTorneo&codice='+codice.val(), true);	
		bellezza.send();
		
		
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == 4) {
				let data = JSON.parse(xhr.responseText);
				console.log(data);			
				
				for (var i = 0; i < data[0].length; i++) {
					var nome=data[0][i].nome;
					sImage.append('<div><h4>'+data[0][i].nome+'</h4><div class="sImage '+nome+'" > <a href="UserControl?action=visualizzaSquadra&nomeSquadra='+ nome +'"> <img src='+data[0][i].teamImage+'></img></a> </div></div>');
				}
			}
		

		}
		xhr.open('GET', 'UserControl;jsessionid='+session+'?action=getSquadreFromTorneo&codTorneo='+$('#torneo').val(), true);	
		xhr.send();
		
	
 })
 
 function testo() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
				
			alert($('textarea').val());
		}
	}
			xhr.open('GET', 'MessaggiControl;jsessionid='+session+'?action=addMessaggio&testo='+$('textarea').val()+'&codice='+$('#torneo').val(), true);	
			xhr.send();
			
}

