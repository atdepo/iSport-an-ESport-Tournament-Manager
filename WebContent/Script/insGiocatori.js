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
	 var xhr = new XMLHttpRequest();
	alert("mammt");
		xhr.onreadystatechange = function() {

			if (xhr.status == 200 && xhr.readyState == 4) {
			
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			alert(data[0]);
				}
			}
			xhr.open('GET', 'SquadreControl?action=getGiocatori', true);
			xhr.send();
	//inserisciGiocatore();

})


function inserisciGiocatore(){
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	var nome= urlParams.get('nomesquadra');
	var div=$ (".inseriamo");
	div.append("<div class='giocatore'>" +
	  		"<input type='text' required placeholder='nickname' id='nickname'>" +
	  		"<input type='text' required placeholder='nome' id='nome'>" +
	  		"<input type='text' required placeholder='cognome' id='cognome'>" +
	  		"<input type='date' required id='data'" +
	  		"<h3> Squadra: "+nome+"</h3></div>");
}



$(function(){
	  $('#upload').change(function(){
		  
	    var input = this;
	    var url = $(this).val();
	    var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
	    if (input.files && input.files[0]&& (ext == "jpeg" || ext == "jpg" || ext== "png" )) {
	    	
	        var reader = new FileReader();
		    reader.readAsDataURL(input.files[0]);

	        reader.onload = function (e) {
		                   
	        var image = new Image();

	        image.src = e.target.result;
	        
	        image.onload = function () {
	        	  var height = this.height;
	        	  var width = this.width;
	        	  if (height > 150 || width > 150) {
	        	    alert("La foto deve essere massimo di 150x150.");
	        	    return false;
	        	  }
	        	  $('#img').attr('src', e.target.result);
	        	  return true;
	        	};
	        
	        
	        }
	       }
	    else
	    {
	      $('#img').attr('src', 'img/default-image.png');  //quando non ci sono immagini
	    }
	  });

	});