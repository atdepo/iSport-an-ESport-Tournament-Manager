	$(document).ready(function(){
	
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			
			if (xhr.status == 200 && xhr.readyState == 4) {
				let data = JSON.parse(xhr.responseText);
				console.log(data);			
				var div=$(".listaTornei");
				for (var i = 0; i < data.length; i++) {
					div.append('<p>Nome Torneo= '+data[i].nome+' <a href="../Admin?action=deleteTorneo&cod='+data[i].codice+'"><i class="fas fa-dumpster-fire"></i></a><br>');	
		
				}
			
			}
		

	}
	xhr.open('GET', '../TournamentControl?action=getTornei', true);		
	xhr.send();
		})
		
		
		$(function() {
			var form=$("#addTecnico");
			form.submit(function() {
				if(nomeCheck()&&cognomeCheck()&&dataCheck()&&indirizzoCheck()&&recapitoCheck()&&CFCheck()&&mailCheck()&&passCheck())
				 return true;
				else return false;	
				
			})
		})
		
			   
	//--------------------------------Funzione per il controllo del campo email------------------------------------------------------------
 function mailCheck(){
		if($('#login-form').css("display")=="block")
			var email=$(".email");
		else
			var email=$("#email");
		
 	var emailReg=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
 	
 	//alert($('#signup-form').css("display")=="block");
 	if($('#login-form').css("display")=="block"){
 		var error=$('.error-mail');	
 	}
 	else
 		var error=email.next();
     
 	if(!email.val()){	//Email non inserita
 			$('span').text("");
			error.text("Inserisci un'e-mail, non ti posso inviare spam altrimenti");
			console.log("email check not passed");
			return false;
		}
		
		else {
			if(!emailReg.test(email.val())){	//Email non corretta
				$('span').text("");
 	    	    error.text("Inserisci un'e-mail credibile dai");
				console.log("email check not passed");
				return false;
 		}
 	     
 		else{	//Controllo passato
 				error.text("");
				console.log("email check passed");
				return true;
			}	
		}
 }




	//--------------------------------------------Funzione per il controllo del campo nome e cognome-------------------------------------------
 function nomeCheck(){
 	var name=$("#nome");

 	var usernameReg=/^[A-Za-z][A-Za-z\'\-]+([\ A-Za-z][A-Za-z\'\-]+)*/;
    let error=name.next();
     
	    if(!name.val()){	//Username non inserito
	    	$('span').text("");
			error.text("Devi mettere uno username prova Gianfranco");
			console.log('nome check not passed');
			return false;
		}
	    else{
		
			if(!usernameReg.test(name.val())){ 	//Username non corretto
				$('span').text("");
				error.text("Magari qualcosa di decente");
				console.log('nome check not passed');
				return false;
			}
			
			else{	//Controllo passato
				
				error.text("");
				console.log('nome check passed');
				return true;
			}
		}
	}
 
 function cognomeCheck() {
	 var cognome=$("#cognome");

	 	var usernameReg=/^[A-Za-z][A-Za-z\'\-]+([\ A-Za-z][A-Za-z\'\-]+)*/;
	 error=cognome.next();
	    if(!cognome.val()){	//Username non inserito
	    	$('span').text("");
			error.text("Devi mettere uno cognome prova Marziano");
			console.log('cognome check not passed');
			return false;
		}
	    else{
		
			if(!usernameReg.test(cognome.val())){ 	//Username non corretto
				$('span').text("");
				error.text("Magari qualcosa di decente");
				console.log('cognome check not passed');
				return false;
			}
			
			else{	//Controllo passato
				
				error.text("");
				console.log('cognome check passed');
				return true;
			}
		}
	
}
	
	
	//--------------------------------------------Funzione per il controllo del campo data-------------------------------------------
function dataCheck(){
	var data=$("#dataN");
	let error=data.next();
	if(!data.val()){
		$('span').text("");
	error.text("Magari inserisci la data");
	console.log('data check not passed');
	return false;
	}
	else return true;
}
//--------------------------------------------Funzione per il controllo del campo indirizzo-------------------------------------------
function indirizzoCheck(){
	var indirizzo=$("#indirizzo");
	let error=indirizzo.next();
	if(!indirizzo.val()){
		$('span').text("");
	error.text("Magari inserisci l'indirizzo");
	console.log('indirizzo check not passed');
	return false;
	}
	else return true;
}
//--------------------------------------------Funzione per il controllo del campo recapito-------------------------------------------
function recapitoCheck() {
	var recapito=$("#recapito");
	var reg=/\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
    let error=recapito.next();

	 if(!recapito.val()){	//Recapito non inserito
	    	$('span').text("");
			error.text("Devi mettere uno recapito prova 3936863580");
			console.log('recapito check not passed');
			return false;
		}
	    else{
			if(!reg.test(recapito.val())){ 	//Username non corretto
				$('span').text("");
				error.text("Magari qualcosa di decente");
				console.log('recapito check not passed');
				return false;
			}
			
			else{	//Controllo passato
				
				error.text("");
				console.log('recapito check passed');
				return true;
			}
	    }
	 }


function CFCheck() {
	var CF=$("#CF");
	var reg=/^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
    let error=CF.next();

	 if(!CF.val()){	//Username non inserito
	    	$('span').text("");
			error.text("Devi mettere un codice fiscale prova MNTNNR61A02H931S");
			console.log('CF check not passed');
			return false;
		}
	    else{
		
			if(!reg.test(CF.val())){ 	//Username non corretto
				$('span').text("");
				error.text("Magari qualcosa di decente");
				console.log('recapito check not passed');
				return false;
			}
			
			else{	//Controllo passato
				
				error.text("");
				console.log('recapito check passed');
				return true;
			}
}
	 }
	//-----------------------------------------Funzione per il controllo del campo password--------------------------------------------------
 function passCheck(){
	
		var password=$("#password");
	
	var passwordReg=/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
    var error=password.next();


	if(!password.val()){	//Password non inserita
		$('span').text("");
		error.text("Devi mettere una password prova Kekko2000!");
		console.log('password check not passed');
		return false;
	}

	else{
		if(!passwordReg.test(password.val())){	//Password non corretta
			$('span').text("");
			error.text("Deve essere almeno 8 caratteri con almeno:un carattere speciale,un lowercase,un UPPERCASE e un numero ");
			console.log('password check not passed');
			return false;
		}
		else{	//Controllo passato
            error.text("");
            console.log('password check passed');
			return true;
		}
	}
}	





 
 
	
	