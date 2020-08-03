/**
 * 
 * 
 * 
 * $(document).ready(function(){ var gioco=$('#gioco');
 * $.getJSON('/src/it/unisa/control/TournamentControl?action=create',
 * function(data){
 * 
 * $.each(data ,function(key,value){ if(key=='nomeGioco') gioco.append('<option
 * value="">'+value+'</option>'); }) })
 * 
 * var select=$('#mode'); select.empty().append('<option
 * value="">---Inizializzata al caricamento---</option>'); var
 * gioco=$('#gioco'); //alert(gioco.children("option:selected").val()); });
 */

$(document).ready(function() {

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {

			let data = JSON.parse(xhr.responseText);
			console.log(data);
			var game = $('#gioco');
			var strutture = $('#strutture');
			var tecnici =$('#tot_tecnici');
			var dataStrutture= data['0'];
			var dataGiochi= data['1'];
			var numTecnici= data['2'];
			document.getElementById("tot_tecnici").setAttribute("max" , numTecnici);

			for (var i = 0; i < dataStrutture.length; i++) {
				strutture.append('<option>' + dataStrutture[i].nome+', '+dataStrutture[i].indirizzo+' - '+dataStrutture[i].CAP+ '</option>');	

			}
		
			for (var i = 0; i < dataGiochi.length; i++) {
				game.append('<option>' + dataGiochi[i].nomeGioco + '</option>');	

			}			
			getMode();
		}
		


	}


	
	xhr.open('GET', 'TournamentControl?action=initTorneo', true);
	xhr.send();

});

/*
 * $.getJSON('TournamentControl?action=initTorneo', function(data,status) {
 * alert('FUNZIONA'); alert(status);
 */

function getMode() {
	var gioco = $('select#gioco option:checked').val();
	//alert(gioco);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			var select = $('#mode');
			select.empty();

			let data = JSON.parse(xhr.responseText);

			for (var i = 0; i < data.length; i++) {
				select.append('<option value="">' + data[i].tipo + '</option>');

			}
		}
	}

	xhr.open('GET', 'TournamentControl?action=getMode&gioco=' + gioco, true);
	xhr.send();

}
