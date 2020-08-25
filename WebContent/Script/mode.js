
$(document).ready(function() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {

			let data = JSON.parse(xhr.responseText);
			console.log(data);
			var game = $('#gioco');
			var sponsor=$('.ks-cboxtags');
			var dataGiochi= data['0'];
			var dataSponsor= data['1'];
			var totTecnici= data['2'];
			var maxTecniciFisici= data['3'];
			$('.max-tecnici-fisici').attr("value",maxTecniciFisici);
			$('.tot-tecnici').attr("value",totTecnici);			
				
			for (var i = 0; i < dataGiochi.length; i++) {
					var nome = dataGiochi[i].nomeGioco.replace(/\s/g, '');
					game.append('<div class="option"><input name="gioco" value="'+dataGiochi[i].nomeGioco+'" onclick="tendina(\''+nome+'\')" type="radio" class="radio" id="'+nome+'"> <label for="'+nome+'">'+dataGiochi[i].nomeGioco+'</label></div>');
			}			
			
			for (var i = 0; i < dataSponsor.length; i++) {

				sponsor.append('<li><input type="checkbox" id="'+dataSponsor[i].nome+'" value="'+dataSponsor[i].nome+'"><label for="'+dataSponsor[i].nome+'">'+dataSponsor[i].nome+'</label></li>')		
			
			}

		}
		
	}
	xhr.open('GET', '../TournamentControl?action=initTorneo', true);
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
		
	xhr.open('GET', '../TournamentControl?action=getStrutture', true);
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
				select.append('<div class="option"><input  value="'+data[i].tipo+'" name="mode"  onclick="tendina(\''+data[i].tipo+'\')" type="radio" class="radio" id="'+data[i].tipo+'"> <label for="'+data[i].tipo+'">'+data[i].tipo+'</label></div>');

			}
		}
	}

	xhr.open('GET', '../TournamentControl?action=getMode&gioco=' + k, true);
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
	if($(".tecniciFisici").empty()&&$(".strutture").empty()){
		
		$("label[for='tecnici_fisici']").show();
		$(".tecniciFisici").append(' <label for="tecnici-fisici">Quanti tecnici desideri avere fisicamente al tuo torneo?</label>'+
				'<div class="number-container tecnici-fisici" title="Premi shift cliccando i selettori per avanzare di +/- 100 e premi CTRL sx per avanzare di +/- 1000">'+
				'<span class="next tecnici-fisici" onclick="gestioneFisici()"></span> <span class="prev tecnici-fisici" onclick="gestioneFisici()"></span> <div class="box-span">'+
				'<span class="number-box-tecnici-fisici">0</span></div>');
		
		numTecnici();
		
		
		$(".strutture").append('<label for="strutture">In che struttura vuoi che sia organizzato il tuo torneo?</label> <div class="container"> <div class="select-box">'+
				'<div class="options-container" id="strutture">'+
				'</div><div class="selected strutture" onclick="menu(\'strutture\')">'+
	             ' Seleziona una Struttura</div></div></div>');
		$("label[for='strutture']").show();
	
		
		getStrutture();
		}
}

function gestioneFisici(){
	if($(event.target).hasClass('next')){
	var max_value=$('.max-tecnici-fisici').val();
	 
    var curr_val=parseInt($('.number-box-tecnici-fisici').text());
    if(event.shiftKey){
    	if(curr_val+100<=max_value)
            $('.number-box-tecnici-fisici').html(curr_val+100); 
    }
    else if(event.ctrlKey){
    	if(curr_val+1000<=max_value)
            $('.number-box-tecnici-fisici').html(curr_val+1000); 
    }
    else if(curr_val+1<=max_value)
        $('.number-box-tecnici-fisici').html(curr_val+1);
}


	else if ($(event.target).hasClass('prev')){
	
	var min_value=0;
	var curr_val=parseInt($('number-box-tecnici-fisici').text());
	if(e.shiftKey){
		if(curr_val-100>=min_value)
			$('number-box-tecnici-fisici').html(curr_val-100); 
		}
	else if(e.ctrlKey){
		if(curr_val-1000>=min_value)
			$('number-box-tecnici-fisici').html(curr_val-1000); 
		}
	else if(curr_val-1>=min_value)
		$('number-box-tecnici-fisici').html(curr_val-1);
		}

}
	


var current=1;
function slide(){
		const slidePage= $('.slidepage');
					
		if($(event.target).hasClass("nextBtn1")){ //JQuery >> tutto
			
			/*var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.status == 200 && xhr.readyState == 4) {
					}
				}
			xhr.open('GET', '../TournamentControl?action=validate', true);
			xhr.send();
			
			*/
			
			if($('.nome-torneo').val()!==""){
				$('header').empty().text("Informazioni sul gioco:");
				slidePage.css("marginLeft","-25%"); //JQuery >> tutto 
				$('#first-step').removeClass("is-active");
				$('#second-step').addClass("is-active");
			}

		}
		else if($(event.target).hasClass("nextBtn2")){
			$('header').empty().text("Organizzazione:");
			slidePage.css("marginLeft","-50%"); //JQuery >> tutto
			$('#second-step').removeClass("is-active");
			$('#third-step').addClass("is-active");

		} 
		else if($(event.target).hasClass("subBtn")){
			
		}
		else if($(event.target).hasClass("prevBtn2")){
			$('header').empty().text("Informazioni di base:");
			slidePage.css("marginLeft","0%"); //JQuery >> tutto
			$('#second-step').removeClass("is-active");
			$('#first-step').addClass("is-active");
			current-=1;
		}
		else if($(event.target).hasClass("prevBtn3")){
			$('header').empty().text("Informazioni sul gioco:");
			slidePage.css("marginLeft","-25%"); //JQuery >> tutto
			$('#third-step').removeClass("is-active");
			$('#second-step').addClass("is-active");
			current-=1;
		}

		
	
		

}


