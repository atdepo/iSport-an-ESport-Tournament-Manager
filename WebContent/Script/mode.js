/**
 * Nel momento in cui il documento FormCreazioneTorneo viene creato, questa funzione  
 * riempie le tendine dei giochi e degli sponsor, che sono le uniche fra tutte le tendine che sono obbligatoriamente mostrate.
 * 
 * 
 */

$(document).ready(function() {

	if($('input[type=radio][name=r-button]:checked').val()=="true"){
		show();
	}
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {

			let data = JSON.parse(xhr.responseText);
			console.log(data);
			var game = $('#gioco');
			var sponsor=$('.ks-cboxtags');
			var dataGiochi= data['0'];
			var tecniciOn= data['1'];
			var tecniciFis= data['2'];
			$('.max-tecnici-fisici').attr("value",tecniciFis);
			$('.tot-tecnici').attr("value",tecniciOn);			
			
			//Questa funzione riempie la tendina dei giochi 
			for (var i = 0; i < dataGiochi.length; i++) {
					var nome = dataGiochi[i].nomeGioco.replace(/\s/g, '');//Questa variabile è stata creata per non assegnare classi multiple ad un tag.
																		  //Es. Desideriamo avere una classe singola "LeagueofLegends" che tre classi "League of Legends"
					
					game.append('<div class="option"><input name="gioco" value="'+dataGiochi[i].nomeGioco+'" onclick="tendina(\''+nome+'\')" type="radio" class="radio" id="'+nome+'"> <label for="'+nome+'">'+dataGiochi[i].nomeGioco+'</label></div>');
			}			

		}
		
	}
	//La richiesta alla servlet
	xhr.open('GET', '../TournamentControl?action=initTorneo', true);
	xhr.send();
	
});


/**
 * Questa funzione aggiunge un click listener al bottone che si occupa della validazione
 * dell'ultima pagina
 * Nel caso in cui la data sia stata inserita in modo errato oppure la struttura sia gia' occupata, allora
 * viene mostrato un errore, altrimenti si procede con il submit del form
 */
/*$(function(){
	$(".subBtn").click(function(){
		var data=$(".data-torneo").val();
		if(data==""){
			$('.error-data').empty().text("Inserisci una data!");
			return false;
		}
		else{
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.status == 200 && xhr.readyState == 4) {
					let data = JSON.parse(xhr.responseText);
					console.log(data);
					var tipo=data['0'];
					var errore=data['1'];
					var check=$('input[type=radio][name=r-button]:checked').val();
					if(check=="true"){
					if(tipo=="null"){
						$("#insTorneo").submit();
					}
					else
						if(tipo=="data"){
							$('.error-data').empty().text(errore);
						}
						else if(tipo=="struttura"){
							$('.error-data').empty().text(errore);
						}
				}
			}
			}
			xhr.open('GET', '../TournamentControl?action=validateTorneo&datatorneo='+data, true);
			xhr.send();
			
		}
	})
	
})

*/

$(function(){
	$(".subBtn").click(function(){
		var data=$(".data-torneo").val();
		if(data==""){
			$('.error-data').empty().text("Inserisci una data!");
			return false;
		}
		else{
			var check=$('input[type=radio][name=r-button]:checked').val();
			var xhr = new XMLHttpRequest();

			if(check=="true"){
				
				var giulio=$('.selected.strutture').text();
				if(giulio!==" Seleziona una Struttura"){
					xhr.open('GET', '../TournamentControl?action=validateTorneo&datatorneo='+data+'&struttura='+giulio, true);
					xhr.send();
				}
				else{
					$('.error-struttura').empty().text("Inserisci una struttura!");
				}
			}
			else if (check=="false"){
				xhr.open('GET', '../TournamentControl?action=validateTorneo&datatorneo='+data, true);
				xhr.send();
			}
			
			xhr.onreadystatechange = function() {
				if (xhr.status == 200 && xhr.readyState == 4) {
					let data = JSON.parse(xhr.responseText);
					console.log(data);
					var tipo=data['0'];
					var errore=data['1'];
					if(tipo=="null"){
						$("#insTorneo").submit();
					}
					else{
						
						if(tipo=="data"){
							$('.error-data').empty().text(errore);
						}
						else if(tipo=="struttura"){
							$('.error-data').empty().text(errore);
						}
					}
				}
			}	
		}
		
		
		
	})
})


/**
 * Questa funzione "apre" la tendina il cui ID è specificato da k
 * @param k l'ID della tendina da aprire
 * 
 */
function menu(k){
	
$("#"+k).toggleClass("active");
	
}


/**
 * Questa funzione si occupa di sostituire come nome scritto sulla tendina, quello del gioco cliccato dalle sue opzioni, 
 * in questo caso identificato tramite il parametro k
 * @param k il nome del gioco da scrivere nella barra principale della tendina
 * 
 */
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


/**
 * 
 * Questa funzione si occupa di chiamare la servlet e di farsi restituire tutte le strutture 
 * presenti all'interno del database e riempire la tendina corrispondente
 * 
 */
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


/**
 * Questa funzione si occupa di prendere tutte le modalità di un dato gioco e riempire la tendina corrispondente.
 * Questa funzione viene chiamata ogni volta che un gioco viene selezionato
 * @param k il gioco dal quale prelevare le modalità
 * @returns
 */
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


/**
 * Questa funzione viene chiamata quando si sceglie di organizzare un torneo on-line,
 * e si occupa di nascondere il numero di tecnici fisici desiderati e le strutture
 * 
 */
function hide(){

	
	$(".tecniciFisici").empty();
	$("label[for='tecnici_fisici']").hide();
	$(".strutture").empty();
	$("label[for='strutture']").hide();
}


/**
 * Questa funzione viene chiamata quando si sceglie di organizzare un torneo fisico,
 * e si occupa di mostrare il numero di tecnici fisici desiderati e le strutture,
 * chiamando opportunamente la funzioni adibita al riempimento della tendina delle strutture.
 * 
 */
function show(){
	if($(".tecniciFisici").empty()&&$(".strutture").empty()){
		
		$("label[for='tecnici_fisici']").show();
		$(".tecniciFisici").append(' <label for="tecnici-fisici">Quanti tecnici desideri avere fisicamente al tuo torneo?</label><div class="info">I tecnici fisici saranno presenti fisicamente al torneo e potrai chiedere qualsiasi cosa<br>(Costo di ogni tecnico:700€)</div>'+
				'<div class="number-container tecnici-fisici" title="Premi shift cliccando i selettori per avanzare di +/- 100 e premi CTRL sx per avanzare di +/- 1000">'+
				'<span class="next tecnici-fisici" onclick="gestioneFisici()"></span> <span class="prev tecnici-fisici" onclick="gestioneFisici()"></span> <div class="box-span">'+
				'<span class="number-box-tecnici-fisici">0</span></div>');		
		
		$(".strutture").append('<label for="strutture">In che struttura vuoi che sia organizzato il tuo torneo?</label>'+
				'<div class="container"> <div class="select-box">'+
				'<div class="options-container" id="strutture">'+
				'</div><div class="selected strutture" onclick="menu(\'strutture\')">'+
	             ' Seleziona una Struttura</div></div></div>');
		$("label[for='strutture']").show();
	
		
		getStrutture();
		}
}


/**
 * Questa funzione si occupa di gestire i bottoni next e previous
 * del campo in cui è possibile scegliere i tecnici fisici.
 * 
 */
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
	var curr_val=parseInt($('.number-box-tecnici-fisici').text());
	if(event.shiftKey){
		if(curr_val-100>=min_value)
			$('.number-box-tecnici-fisici').html(curr_val-100); 
		}
	else if(event.ctrlKey){
		if(curr_val-1000>=min_value)
			$('.number-box-tecnici-fisici').html(curr_val-1000); 
		}
	else if(curr_val-1>=min_value)
		$('.number-box-tecnici-fisici').html(curr_val-1);
		}

}
	

var current=1;
/**
 * Questa funzione si occupa di gestire lo slide del form a destra e a sinistra quando 
 * i bottoni next e previous sono stati premuti. Si occupa anche di gestire il fatto 
 * che i campi obbligatori siano stati riempiti. 
 */
function slide(){
	
		const slidePage= $('.slidepage');
					
		if($(event.target).hasClass("nextBtn1")){ //JQuery >> tutto
			
			if($('.nome-torneo').val()!==""){
				$('header').empty().text("Informazioni sul gioco:");
				slidePage.css("marginLeft","-25%"); //JQuery >> tutto 
				$('#first-step').removeClass("is-active");
				$('#second-step').addClass("is-active");
			}

		}
		else if($(event.target).hasClass("nextBtn2")){
			
			if($('.gioco').text()!=="Gioco di Riferimento"&&$('.mode').text()!=="Modalità di gioco"){
				$('header').empty().text("Organizzazione:");
				slidePage.css("marginLeft","-50%"); //JQuery >> tutto
				$('#second-step').removeClass("is-active");
				$('#third-step').addClass("is-active");
			}
		} 
		else  if($(event.target).hasClass("prevBtn2")){
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


