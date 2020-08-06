
$(document).ready(function() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {

			let data = JSON.parse(xhr.responseText);
			console.log(data);
			var game = $('#gioco');
			var strutture = $('#strutture');
			var dataStrutture= data['0'];
			var dataGiochi= data['1'];
			var numTecnici= data['2'];
			document.getElementById("tot_tecnici").setAttribute("max" , numTecnici);
			
			for (var i = 0; i < dataStrutture.length; i++) {
					var nome = dataStrutture[i].nome.replace(/\s/g, '');
					strutture.append('<div class="option"><input required name="strutture" onclick="tendina(\''+nome+'\')" type="radio" class="radio" id="'+nome+'" value="'+dataStrutture[i].nome+'"> <label for="'+nome+'">'+dataStrutture[i].nome+', '+dataStrutture[i].indirizzo+' - '+dataStrutture[i].CAP+' </label></div>');	
			}
				
			for (var i = 0; i < dataGiochi.length; i++) {
					var nome = dataGiochi[i].nomeGioco.replace(/\s/g, '');
					game.append('<div class="option"><input name="gioco" onclick="tendina(\''+nome+'\')" type="radio" class="radio" id="'+nome+'"> <label for="'+nome+'">'+dataGiochi[i].nomeGioco+'</label></div>');

			}			
			//getMode();
		}
		
	}

	xhr.open('GET', 'TournamentControl?action=initTorneo', true);
	xhr.send();

});

function menu(k){
	
$("#"+k).toggleClass("active");
	
}
function tendina(k){
	var selected=$('.selected.'+event.target.name);
	var optionsContainer = $("#"+event.target.name);
	selected.text($("label[for='"+k+"']").html());
    optionsContainer.toggleClass("active");
    if(event.target.name==="gioco"){
    	
    	$('.selected.mode').text("Modalità di gioco");
    getMode($("label[for='"+k+"']").html());
    }
}


function getMode(k) {
	//var gioco = $('select#gioco option:checked').val();
	//alert($("#"+event.target.for).html());
	//var gioco = $("#"+event.target.for).html();
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			var select = $('#mode');
			select.empty();

			let data = JSON.parse(xhr.responseText);

			for (var i = 0; i < data.length; i++) {
				//select.append('<option>' + data[i].tipo + '</option>');
				select.append('<div class="option"><input name="mode" onclick="tendina(\''+data[i].tipo+'\')" type="radio" class="radio" id="'+data[i].tipo+'"> <label for="'+data[i].tipo+'">'+data[i].tipo+'</label></div>');

			}
		}
	}

	xhr.open('GET', 'TournamentControl?action=getMode&gioco=' + k, true);
	xhr.send();

}


function numTecnici(){

	document.getElementById('tecnici_fisici').setAttribute("max",document.getElementById('tot_tecnici').value);
	
}


function validateTournament(){
	
	
	
}

