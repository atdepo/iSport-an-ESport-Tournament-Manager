
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
		
			form.append('<div class="page page-'+j+'"><div class="field"><label for="nickname" class="testlabel">Nickname</label><input type="text" class="feedback-input nome-torneo" placeholder="SuperMario64" name="nickname"></div><div class="field"><label for="nome" class="testlabel">Nome</label><input type="text" class="feedback-input nome-torneo" placeholder="Mario" name="nome"></div><div class="field"><label for="cognome" class="testlabel">Cognome</label><input type="text" class="feedback-input nome-torneo" placeholder="Rossi" name="cognome"></div><div class="field"><label for="ruolo" class="testlabel">Ruolo</label><input type="text" class="feedback-input nome-torneo" placeholder="Jungler" name="ruolo"></div><div class="field" class="testlabel"><label for="nascita" class="testlabel">Data di Nascita</label><input type="date" class="feedback-input nome-torneo" name="nascita"></div><div class="field"><div class="form-group file-area"><label for="images" class="testlabel">Immagine<span>La tua immagine deve essere 150x150</span></label><input type="file" name="images" id="images-<%=i%>" required="required"/><div class="file-dummy"><div class="success">Hai inserito un immagine BRAV</div><div class="default">Favorire documento,grazie!</div></div></div></div><div class="field-btn"><input type="button" class="button-blue prevBtn'+j+'" onclick="cambiaPagina()" value="Prev"><input type="button" class="button-blue nextBtn'+j+'" onclick="cambiaPagina()" value="Next"></div></div>')
		}
			}
		}
	
		xhr.open('GET', '../SquadreControl?action=getGiocatori', true);
		xhr.send();
}


function cambiaPagina(){
	
	var called = $(event.target).attr("class").replace(/\D/g,'');
	if($(event.target).val()=="Prev"){
		var tmp = (called-1)*25;
	
		
		$('#'+called+'-step').removeClass("is-active");
		$('#'+(called-1)+'-step').addClass("is-active");
		
		var change= '-'+tmp+'%';
		
		$('.slidepage').css("marginLeft",change);
		
		
	}
	else 
		if($(event.target).val()=="Next" ){
			var tmp = (1+parseInt(called))*25;
		
			
			$('#'+called+'-step').removeClass("is-active");			
			$('#'+(1+parseInt(called))+'-step').addClass("is-active");
			
			var change= '-'+tmp+'%';
			
			$('.slidepage').css("marginLeft",change);
			
			
			

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