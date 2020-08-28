
$(document).ready(function(){

	//inserisciGiocatore();
	creaSteps();
	
})

	


function creaSteps() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {
		
		let data = JSON.parse(xhr.responseText);
		console.log(data);
		var multi=$(".multi-steps");
		var form=$("#the-form");
		
		for(var j=1;j<=parseInt(data);j++){	//For per gli indicatori del multistep
			
			multi.append('<li id="'+j+'-step">Giocatore '+j+'</li>')
		
			form.append('<div class="page page-'+j+'"><div class="field"><label for="nickname-player-'+j+'" class="form-label">Nickname</label><input type="text" class="feedback-input" placeholder="SuperMario64" name="nickname-player-'+j+'"></div><div class="field"><label for="nome-giocatore-'+j+'" class="form-label">Nome</label><input type="text" class="feedback-input" placeholder="Mario" name="nome-giocatore-'+j+'"></div><div class="field"><label for="cognome-giocatore-'+j+'" class="form-label">Cognome</label><input type="text" class="feedback-input" placeholder="Rossi" name="cognome-giocatore-'+j+'"></div><div class="field"><label for="ruolo-giocatore-'+j+'" class="form-label">Ruolo</label><input type="text" class="feedback-input" placeholder="Jungler" name="ruolo-giocatore-'+j+'"></div><div class="field" class="form-label"><label for="nascita-giocatore-'+j+'" class="form-label">Data di Nascita</label><input type="date" class="feedback-input" name="nascita-giocatore-'+j+'"></div><div class="field"><label for="images" class="form-label">Immagine<span>La tua immagine deve essere 150x150</span></label><input type="file" name="images" id="images-'+j+'>" required="required"/></div><div class="field-btn"><input type="button" class="button-blue prevBtn'+j+'" onclick="cambiaPagina()" value="Prev"><input type="button" class="button-blue nextBtn'+j+'" onclick="cambiaPagina()" value="Next"></div></div>')
		}
			}
		}
	
		xhr.open('GET', '../SquadreControl?action=getGiocatori', true);
		xhr.send();
}


function cambiaPagina(){
	
	var called = $(event.target).attr("class").replace(/\D/g,'');
	if($(event.target).val()=="Prev"){
		var tmp = (called-1)*18;
	
		
		$('#'+called+'-step').removeClass("is-active");
		$('#'+(called-1)+'-step').addClass("is-active");
		
		var change= '-'+tmp+'%';
		
		$('.slidepage').css("marginLeft",change);
		
		
	}
	else 
		if($(event.target).val()=="Next" ){
			var tmp = (1+parseInt(called))*18;
		
			
			$('#'+called+'-step').removeClass("is-active");			
			$('#'+(1+parseInt(called))+'-step').addClass("is-active");
			
			var change= '-'+tmp+'%';
			
			$('.slidepage').css("marginLeft",change);
			
			
			

		}
		
	
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