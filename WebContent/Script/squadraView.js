/**
 * 
 */
 
 $(document).ready(function(){
	 	var xhr = new XMLHttpRequest();
		var datiP=$('.dati');
		var pImage=$('.pImage');
		var nomeS=$('.nomeS');
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == 4) {
				let data = JSON.parse(xhr.responseText);
				console.log(data);			
				nomeS.append('<div class=nomeS>'+data[0][i].nome +' </div> ');	
				
				for (var i = 0; i < data[0].length; i++) {
					pImage.append('<div class=pImage> <img src='+data[0][i].teamImage+'></img> </div> ');
					datiP.append('<div class=datiP>'+data[0][i].nome +' </div>');
				}
			}
		

		}
		xhr.open('GET', 'SquadreControl?action=getGiocatoriFromSquadra&nomeSquadra=', true);	
		xhr.send();
 })