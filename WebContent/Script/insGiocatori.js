
$(document).ready(function(){
	creaSteps();

})

	
$(function() {
	$(".nextBtn1").click(function() {
		alert("mammina");
	})
	});

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
			
				form.append('<div class="page page-'+j+'">'+

				'<div class="field">'+

				'<label for="nickname-player-'+j+'" class="form-label">Nickname</label>'+
				'<input type="text" class="feedback-input nickname-player-'+j+'" placeholder="SuperMario64" name="nickname-player-'+j+'">'+
				'<span class="error nick"></span>'+
				'</div>'+
				
				'<div class="field">'+
				'<label for="nome-giocatore-'+j+'" class="form-label">Nome</label>'+
				'<input type="text" class="feedback-input" placeholder="Mario" name="nome-giocatore-'+j+'">'+
				'<span class=" error nome"></span>'+
				'</div>'+
				
				'<div class="field">'+
				'<label for="cognome-giocatore-'+j+'" class="form-label">Cognome</label>'+
				'<input type="text" class="feedback-input" placeholder="Rossi" name="cognome-giocatore-'+j+'">'+
				'<span class=" error cognome"></span>'+
				'</div>'+
				
				'<div class="field">'+
				'<label for="ruolo-giocatore-'+j+'" class="form-label">Ruolo</label>'+
				'<input type="text" class="feedback-input" placeholder="Jungler" name="ruolo-giocatore-'+j+'">'+
				'<span class=" error ruolo"></span>'+
				'</div>'+
				
				'<div class="field" class="form-label">'+
				'<label for="nascita-giocatore-'+j+'" class="form-label">Data di Nascita</label>'+
				'<input type="date" class="feedback-input" name="nascita-giocatore-'+j+'">'+
				'<span class=" error nascita"></span>'+
				'</div>'+
				
				'<div class="field"><label for="images" class="form-label">Inserisci una immagine massimo 150x150</label>'+
				'<input type="file" name="images" id="images-'+j+'>" required="required">'+
				'<span class=" error img"></span>'+
				'</div>'+
				
				'<div class="field-btn">'+
				'<input type="button" class="button-blue prevBtn'+j+'" onclick="cambiaPagina()" value="Prev">'+
				'<input type="button" class="button-blue nextBtn'+j+'" onclick="cambiaPagina()" value="Next">'+
				'</div>'+
				'</div>');
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
			
			var xhr = new XMLHttpRequest();
			if(called==0)
				xhr.open('GET', '../SquadreControl?action=validateTeam&teamName='+$('.nome-squadra').val(), true);
			else
				xhr.open('GET', '../SquadreControl?action=validatePlayer&nick='+$('.nickname-player-'+called).val(), true);

			xhr.send();
			
			xhr.onreadystatechange = function() {

				if (xhr.status == 200 && xhr.readyState == 4) {
					let data = JSON.parse(xhr.responseText);
					console.log(data);
					if(data['0']!=="null"){
						if(called==0)
						$('.error-name-'+called).text(data['0']);
						else{
							$('.nickname-player-'+called).next().text(data['0']);
						}
					}
					else{
						if(called==0)
							$('.error-name-'+called).text("");
						else
							$('.nickname-player-'+called).next().text("");
							
						var tmp = (1+parseInt(called))*18;
						$('#'+called+'-step').removeClass("is-active");			
						$('#'+(1+parseInt(called))+'-step').addClass("is-active");
						var change= '-'+tmp+'%';
						$('.slidepage').css("marginLeft",change);
						//alert('no errore!');
					}
				
				}
			}
		}
}


function validateCampi(i){
	var regGeneral="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"
	
	var nickname=$("nickname-player-"+i);
	var nome=$("nome-giocatore-"+i);
	var cognome=$("cognome-giocatore"+i);
	
	if(!nickname.val()){//Nickname non inserito
		var error=nickname.next();
		$('span').text("");
		error.text("Inserisci un nickname");
		console.log("nickname non inserito");
		return false;
	
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