$(document).ready(function(){
	
	/*var div=$ (".inseriamo");
	  var numeroGiocatori= $("#giocatori").val();
	  
	  for (let i = 0; i < numeroGiocatori; i++) {
		  div.append("<div class='giocatore'>" +
		  		"<input type='text' placeholder='nickname' id='nickname'>" +
		  		"<input type='text' placeholder='nome' id='nome'>" +
		  		"<input type='text' placeholder='cognome' id='cognome'>" +
		  		"<input type='date' id='data'" +
		  		"<h3>"+$('#nSquadra').val()+"</h3></div>");
	  }
	  
	  */
	inserisciGiocatore();

})


function inserisciGiocatore(){
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	var nome= urlParams.get('nomesquadra');
	var div=$ (".inseriamo");
	div.append("<div class='giocatore'>" +
	  		"<input type='text' placeholder='nickname' id='nickname'>" +
	  		"<input type='text' placeholder='nome' id='nome'>" +
	  		"<input type='text' placeholder='cognome' id='cognome'>" +
	  		"<input type='date' id='data'" +
	  		"<h3> Squadra: "+nome+"</h3></div>");
}