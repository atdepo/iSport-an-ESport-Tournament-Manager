
$(document).ready(function() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {

			let data = JSON.parse(xhr.responseText);
			console.log(data);
			var game = $('#gioco');
			
			var dataGiochi= data['0'];
			var numTecnici= data['1'];
			document.getElementById("tot_tecnici").setAttribute("max" , numTecnici);
			
				
			for (var i = 0; i < dataGiochi.length; i++) {
					var nome = dataGiochi[i].nomeGioco.replace(/\s/g, '');
					game.append('<div class="option"><input name="gioco" onclick="tendina(\''+nome+'\')" type="radio" class="radio" id="'+nome+'"> <label for="'+nome+'">'+dataGiochi[i].nomeGioco+'</label></div>');

			}			

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

function getStrutture(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			
			var strutture = $('#strutture');
			//var dataStrutture= data['0'];
			for (var i = 0; i < data.length; i++) {
				var nome = data[i].nome.replace(/\s/g, '');
				strutture.append('<div class="option"><label for="'+nome+'">'+data[i].nome+', '+data[i].indirizzo+' - '+data[i].CAP+' </label><input required name="strutture" onclick="tendina(\''+nome+'\')" type="radio" class="radio" id="'+nome+'" value="'+data[i].nome+'"> </div>');	
		}
		
		}
	}
		
	xhr.open('GET', 'TournamentControl?action=getStrutture', true);
	xhr.send();
	
	
}



function getMode(k) {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			var select = $('#mode');
			select.empty();

			let data = JSON.parse(xhr.responseText);

			for (var i = 0; i < data.length; i++) {
				select.append('<div class="option"><input name="mode" onclick="tendina(\''+data[i].tipo+'\')" type="radio" class="radio" id="'+data[i].tipo+'"> <label for="'+data[i].tipo+'">'+data[i].tipo+'</label></div>');

			}
		}
	}

	xhr.open('GET', 'TournamentControl?action=getMode&gioco=' + k, true);
	xhr.send();

}

function numTecnici(){

	$('.tecniciFisici').attr("max",$("#tot_tecnici").val());
}


function hide(){

	
	$(".tecniciFisici").empty();
	$("label[for='tecnici_fisici']").hide();
	$(".strutture").empty();
	$("label[for='strutture']").hide();
}

function show(){
	$("label[for='tecnici_fisici']").show();
	$(".tecniciFisici").append('<label for="tecnici_fisici">Di cui presenti fisicamente </label>'+ 
							   '<input type="number" min="0"  max="10" name="tecnici_fisici" id="tecnici_fisici" required>');
	
	numTecnici();
	
	
	$(".strutture").append('<label for="strutture">Struttura</label> <div class="container"> <div class="select-box">'+
			'<div class="options-container" id="strutture">'+
			'</div><div class="selected strutture" onclick="menu(\'strutture\')">'+
             ' Seleziona una Struttura</div></div></div>');
	$("label[for='strutture']").show();

	
	getStrutture();
}


