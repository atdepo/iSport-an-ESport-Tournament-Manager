
/* Questa funzione aggiunge un listener al form, nel momento in cui viene effettuato un submit
	* nel form di registrazione di un nuovo utente viene controllato se i campi sono stati inseriti correttamente
	* prima di passare il controllo al backend per il controllo di ulteriori informazioni
	*/
	$(function(){
		$('.sub').submit(function () { 
		
			return mailCheck() && userCheck() && ivaCheck() && passCheck();	
		});
		
	});


	//I campi di errore vengono nascosti per essere mostrati quando serve
	
	$('span').text("");
	   
	//--------------------------------Funzione per il controllo del campo email------------------------------------------------------------
 function mailCheck(){
 	var email=$("#email");
 	var emailReg=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    let error=email.next();
     
 	if(!email.val()){	//Email non inserita
			error.text("Inserisci un'e-mail, non ti posso inviare spam altrimenti");
			console.log("email check not passed");
			return false;
		}
		
		else {
			if(!emailReg.test(email.val())){	//Email non corretta
				
 	    	    error.text("Inserisci un'e-mail credibile dai");
				console.log("email check not passed");
				return false;
 		}
 	     
 		else{	//Tutt a post
 				error.text("");
				console.log("email check passed");
				return true;
			}	
		}
 }




	//--------------------------------------------Funzione per il controllo del campo username-------------------------------------------
 function userCheck(){
 	var username=$("#username");
 	var usernameReg=/^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$/;
    let error=username.next();
     
	    if(!username.val()){	//Username non inserito
			error.text("Devi mettere uno username prova xxSpinnerKillerxx");
			console.log('username check not passed');
			return false;
		}
	    else{
		
			if(!usernameReg.test(username.val())){ 	//Username non corretto
				error.text("Magari qualcosa di decente");
				console.log('username check not passed');
				return false;
			}
			
			else{	//Tutt a post
				
				error.text("");
				console.log('username check passed');
				return true;
			}
		}
	}
	
	


	//-----------------------------------------Funzione per il controllo del campo password--------------------------------------------------
 function passCheck(){
	var password=$("#password");
	var passwordReg=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*+-/\?&])[A-Za-z\d@$!%*?&]{8,}$/;
    let error=password.next();

	if(!password.val()){	//Password non inserita
		error.text("Devi mettere una password prova Kekko2000!");
		console.log('password check not passed');
		return false;
	}

	else{
		if(!passwordReg.test(password.val())){	//Password non corretta
			error.text("Deve essere almeno 8 caratteri con almeno:un carattere speciale,un lowercase,un UPPERCASE e un numero ");
			console.log('password check not passed');
			return false;
		}
		else{	//Tutt a post
            error.text("");
            console.log('password check passed');
			return true;
		}
	}
}	




	//---------------------------------------Funzione per il controllo del campo partita IVA-----------------------------------------------------
	function ivaCheck(){
		var iva=$("#iva");
		var ivaReg=/^[0-9]{11}$/;
        let error=iva.next();

		if(iva.val() && !ivaReg.test(iva.val())){	//Iva non corretta
			error.text("Iva non corretta");
			console.log('Iva check not passed');
			return false;
		}
		else{	//Tutt a post
			error.text("");
			console.log('Iva check passed');
			return true;
		}
    }
    



//---------------Funzioni per cambiare il tipo di azione da compiere(login|register)----------------------------------
function toggleSignup(){
         document.getElementById("login-toggle").style.backgroundColor="#fff";
         document.getElementById("login-toggle").style.color="#222";
         document.getElementById("signup-toggle").style.backgroundColor="#008080";
         document.getElementById("signup-toggle").style.color="#fff";
         document.getElementById("login-form").style.display="none";
         document.getElementById("signup-form").style.display="block";
}
 
function toggleLogin(){
         document.getElementById("login-toggle").style.backgroundColor="#008080";
         document.getElementById("login-toggle").style.color="#fff";
         document.getElementById("signup-toggle").style.backgroundColor="#fff";
         document.getElementById("signup-toggle").style.color="#222";
         document.getElementById("signup-form").style.display="none";
         document.getElementById("login-form").style.display="block";
}
         
 
 
	
	