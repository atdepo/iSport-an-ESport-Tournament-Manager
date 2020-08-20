
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
	error=$(".error");
	error.hide();
	   
	//--------------------------------Funzione per il controllo del campo email------------------------------------------------------------
    function mailCheck(){
    	var email=$("#email");
    	var emailReg=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    	   
    	if(!email.val()){	//Email non inserita
			email.next().show().text("Inserisci un'e-mail, non ti posso inviare spam altrimenti");
			console.log("email check not passed");
			return false;
		}
		
		else {
			if(!emailReg.test(email.val())){	//Email non corretta
				
    	    	email.next().show().text("Inserisci un'e-mail credibile dai");
				$(this).addClass("er");
				console.log("email check not passed");
				return false;
    		}
    	     
    		else{	//Tutt a post
    	  		error.hide();
				console.log("email check passed");
				return true;
			}	
		}
    }




	//--------------------------------------------Funzione per il controllo del campo username-------------------------------------------
    function userCheck(){
    	var username=$("#username");
    	var usernameReg=/^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$/;
	   
	    if(!username.val()){	//Username non inserito
			username.next().show().text("Devi mettere uno username prova xxSpinnerKillerxx");
			console.log('username check not passed');
			return false;
		}
	    else{
		
			if(!usernameReg.test(username.val())){ 	//Username non corretto
				username.next().show().text("Magari qualcosa di decente");
				$(this).addClass("er");
				console.log('username check not passed');
				return false;
			}
			
			else{	//Tutt a post
				
				error.hide();
				console.log('username check passed');
				return true;
			}
		}
	}
	
	


	//-----------------------------------------Funzione per il controllo del campo password--------------------------------------------------
	function passCheck(){
		var password=$("#password");
		var passwordReg=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
   
		if(!password.val()){	//Password non inserita
			password.next().show().text("Devi mettere una password prova Kekko2000!");
			console.log('password check not passed');
			return false;
		}

		else{
			if(!passwordReg.test(password.val())){	//Password non corretta
				password.next().show().text("Deve essere almeno 8 caratteri con almeno:un carattere speciale,un lowercase,un UPPERCASE e un numero ");
				$(this).addClass("er");
				console.log('password check not passed');
				return false;
			}
			else{	//Tutt a post
				error.hide();
				console.log('password check passed');
				return true;
			}
		}
	}	




	//---------------------------------------Funzione per il controllo del campo partita IVA-----------------------------------------------------
	function ivaCheck(){
		var iva=$("#iva");
		var ivaReg=/^[0-9]{11}$/;
  
		if(iva.val() && !ivaReg.test(iva.val())){	//Iva non corretta
			iva.next().show().text("Iva non corretta");
			$(this).addClass("er");
			console.log('Iva check not passed');
			return false;
		}
		else{	//Tutt a post
			error.hide();
			console.log('Iva check passed');
			return true;
		}
	}
	
	