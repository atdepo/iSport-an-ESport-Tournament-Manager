$(document).ready(function(){
	var div=$ (".inseriamo");
	  var numeroGiocatori= $("#giocatori").val();
	  
	  for (let i = 0; i < numeroGiocatori; i++) {
		  div.append("<div class='giocatore'>" +
		  		"<input type='text' placeholder='nickname' id='nickname'>" +
		  		"<input type='text' placeholder='nome' id='nome'>" +
		  		"<input type='text' placeholder='cognome' id='cognome'>" +
		  		"<input type='date' id='data'" +
		  		"<h3>il nome della squadra lo prendiamo dalla sessione</h3></div");
	  }
})