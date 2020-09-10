

$(document).ready(function(){
	 	var xhr = new XMLHttpRequest();
		//var nomeT=$('.nomeT');
		//var nomeS=$('.nomeS');
		var sImage=$('.loghi');
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
		xhr.open('GET', 'UserControl?action=getSquadreFromTorneo&codTorneo='+$('#torneo').val(), true);	
		xhr.send();
 })

