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
				let data = JSON.parse(xhr.responseText);
				console.log(data);			
				
				for (var i = 0; i < data.length; i++) {
					//nomeS.append('<div class=nomeS>'+data[i].nome +' </div> ');	
					//pImage.append('<div class=pImage> <img src='+data[i].teamImage+'></img> </div> ');
					//datiP.append('<div class=datiP>'+data[i].nome +' </div>');
					giocatori.append('<div><h3>'+data[i].nickname+'</h3><div class="'+data[i].nome+'"><img src='+data[i].imgPlayer+'></img></div></div> ');
				}
			}
		}
		xhr.open('GET', 'UserControl?action=getGiocatoriFromSquadra&nomeSquadra='+$('.teamName').val(), true);	
		xhr.send();
 })