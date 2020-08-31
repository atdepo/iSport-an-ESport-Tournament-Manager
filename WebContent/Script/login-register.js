
/* Questa funzione aggiunge un listener al form che, nel momento in cui viene effettuato un submit
	* nel form di registrazione di un nuovo utente, viene controllato se i campi sono stati inseriti correttamente
	* prima di passare il controllo al backend per il controllo di ulteriori informazioni
	*/
	$(function(){
		$('.sub').submit(function () { 
		
			var res= mailCheck() && userCheck() && ivaCheck() && passCheck();	
			if(res){
				var d = new Date();
				var month = d.getMonth()+1;
				var day = d.getDate();
				var output = d.getFullYear() + '/' +
				    (month<10 ? '0' : '') + month + '/' +
				    (day<10 ? '0' : '') + day;
				$('#data').val(output);
				return true;
			}
			else
				return false;
		});
		
	});
	
	 //Test per l'immagine 150x150
	 
	 $(function() {
		 $(".immagine").change(function() {
			var file = $(this)[0].files[0];
			var img=new Image();
			var imgwidth = 0;
			var imgheight = 0;
			img.src = _URL.createObjectURL(file);
			img.onload=function(){
			
			alert("EIO");
			imgwidth = this.width;
			imgheight = this.height;
			alert(imgwidth);
			alert(imgheight);
			
			}	
				});
		
	})
	
	//Questa funzione aggiunge un listener al form che controlla se la mail è stata inserita correttamente
	$(function(){
		$('.log').submit(function(){
			
			return mailCheck() && passCheck();
		});
		
	});
	

	
	
	//I campi di errore vengono nascosti per essere mostrati quando serve
/*	$(document).ready(function(){
		$('span').text("");
	})
	*/   
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




	//--------------------------------------------Funzione per il controllo del campo username-------------------------------------------
 function userCheck(){
 	var username=$("#username");
 	var usernameReg=/^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$/;
    let error=username.next();
     
	    if(!username.val()){	//Username non inserito
	    	$('span').text("");
			error.text("Devi mettere uno username prova xxSpinnerKillerxx");
			console.log('username check not passed');
			return false;
		}
	    else{
		
			if(!usernameReg.test(username.val())){ 	//Username non corretto
				$('span').text("");
				error.text("Magari qualcosa di decente");
				console.log('username check not passed');
				return false;
			}
			
			else{	//Controllo passato
				
				error.text("");
				console.log('username check passed');
				return true;
			}
		}
	}
	
	


	//-----------------------------------------Funzione per il controllo del campo password--------------------------------------------------
 function passCheck(){
	if($('#login-form').css("display")=="block")
		var password=$('.password');
	else
		var password=$("#password");
	
	var passwordReg=/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
   
	if($('#login-form').css("display")=="block")
		var error=$('.error-cred');
	else
		var error=$('.psw');

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




	//---------------------------------------Funzione per il controllo del campo partita IVA-----------------------------------------------------
	function ivaCheck(){
		var iva=$("#iva");
		var ivaReg=/^[0-9]{11}$/;
        let error=iva.next();

		if(iva.val() && !ivaReg.test(iva.val())){	//Iva non corretta
			$('span').text("");
			error.text("Iva non corretta");
			console.log('Iva check not passed');
			return false;
		}
		else{	//Controllo passato
			error.text("");
			console.log('Iva check passed');
			return true;
		}
    }
//--------------------------------------Funzione per lo show della password------------------------------------------   
    function showPass() {
    	
    	if($('#login-form').css("display")=="block")
    		var x=$('.password');
    	else
    		var x=$('#password');
    	
    	if(x.attr("type")==="password")
    		x.attr("type","text");
    	else
    		x.attr("type","password");

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
         
 
 
	
	