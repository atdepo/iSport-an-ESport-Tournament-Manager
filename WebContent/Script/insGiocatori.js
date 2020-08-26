
$(document).ready(function(){

	//inserisciGiocatore();

})


function inserisciGiocatore(){
	/*const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	var nome= urlParams.get('nomesquadra');
	var div=$ (".inseriamo");
	div.append("<div class='giocatore'>" +
	  		"<input type='text' required placeholder='nickname' id='nickname'>" +
	  		"<input type='text' required placeholder='nome' id='nome'>" +
	  		"<input type='text' required placeholder='cognome' id='cognome'>" +
	  		"<input type='date' required id='data'" +
	  		"<h3> Squadra: "+nome+"</h3></div>");*/
	
	
	
	
	
}

function cambiaPagina(){
	var called = $(event.target).attr("class").replace(/\D/g,'');
	
	if($(event.target).val()=="Prev"){
		var tmp = (called-2)*25;
	
		
		$('#'+called+'-step').removeClass("is-active");
		$('#'+(called-1)+'-step').addClass("is-active");
		
		var change= '-'+tmp+'%';
		
		$('.slidepage').css("marginLeft",change);
		setTimeout(() => {$('.page-'+called).css("display","none");
		$('.page-'+(called-1)).css("display","block");}, 300);
		
	}
	else 
		if($(event.target).val()=="Next" && called<$('#numGiocatori').val()){
			var tmp = called*25;
			var src= 1+parseInt(called);
			
			$('#'+called+'-step').removeClass("is-active");			
			$('#'+(src)+'-step').addClass("is-active");
			
			var change= '-'+tmp+'%';
			
			$('.slidepage').css("marginLeft",change);
			
			setTimeout(() => {$('.page-'+called).css("display","none");
			$('.page-'+(src)).css("display","block");}, 300);
			

		}
		
	
}

function sleep(milliseconds) {
	  const date = Date.now();
	  let currentDate = null;
	  do {
	    currentDate = Date.now();
	  } while (currentDate - date < milliseconds);
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