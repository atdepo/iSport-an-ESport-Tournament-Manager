
 $(document).ready(function(){
 	/*
 	if($('#user-pIVA').val()!=="           "){
 		$('.dati').append('<div class="gertrude"><div class="field"><label for="iva" class="testlabel">Partita IVA</label><input type="text" class="iva" disabled placeholder="'+$('#user-pIVA').val()+'" name="pIVA"><i class="fas fa-user-edit iva"></i></div></div>')
 	}*/
 //TORNEI DELL'UTENTE
 	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);			
			var div=$("#mieiTornei");
			if(data.length==0)
				div.append("<h3>Non hai tornei, dai ordina il tuo primo torneo dalla sezione Crea Tornei</h3>");
			for (var i = 0; i < data.length; i++) {
				div.append('<a href="../UserControl?action=visualizza&codtorneo='+data[0][i].codice+'">  <h3> '+data[0][i].nome+'</h3><h3> '+data[0][i].data+'</h3><h3> '+data[1][i]+'</h3><h3> '+data[0][i].indirizzoStruttura+'</h3><h3> '+data[0][i].codGioco+'</h3><br></a>');	
	
			}
		
		}
	

}
xhr.open('GET', '../UserControl?action=getMieiTornei', true);		
xhr.send();
 })
 
function getSquadra(codice){
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '../UserControl?action=visualizza&codtorneo='+codice, true);	
	xhr.send();
}

 
 
 
 function confermaMod(){
	 var Nome=$('#username').val();
     var Email=$('#email').val();
     var IVA=$('#iva').val();
     
   //  var Img=$('#images-0').val();
     var error=$("#errorImg").text()
     
     	var file = $("#images-0")[0].files[0]; //this is the input where I can choose the file
		
    

      
		
    if(mailCheck()&&userCheck()&&ivaCheck()&&oldPassCheck()&&passCheck()&&error==""){
    	
    	alert($('#vecchia').val());
    	alert($('#nuova').val());
    	

           $.ajax({
               url: 'UserControl?action=modificaDati',
               type: 'POST',
               data: 'nome='+Nome+'&email='+Email+'&iva='+IVA+'&oldEmail='+$('#UtenteEmail').val()+'&oldPass='+$('#vecchia').val()+'&newPass='+$('#nuova').val(),
             
           });
       
    }
    	
    	

    
     
 }

 
 function oldPassCheck() {
		var passwordReg=/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	 var password=$("#vecchia");
	 var error=password.next();
		if(!password.val()){	//Password non inserita
			$('span').text("");
			return true;
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
 
 function passCheck(){
		var passwordReg=/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var password=$("#nuova");
		var passwordReg=/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var error=password.next();

		if(!password.val()){	//Password non inserita
			$('span').text("");
			return true;
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
 
 
	function checkImg() {
		var file = $(this)[0].files[0];
		var img=new Image();
		var imgwidth = 0;
		var imgheight = 0;
		var error=$("#images-0").next();
		if(typeof file!==typeof undefined){
		img.src = URL.createObjectURL(file);
		img.onload=function(){
		
		imgwidth = this.width;
		imgheight = this.height;
		if(imgwidth>parseInt(150)&&imgheight>parseInt(150)){
			error.text("Inserisci un'immagine di massimo 150x150"); 
			$(".signup").prop('disabled', true);
			}
		else {
			error.text(""); 
			$(".signup").prop('disabled', false);
			}
		}
		
		}else
			{error.text("");$(".signup").prop('disabled', false);return true;}
			} 
	
	
	$(function() {
		 $("#images-0").change(checkImg);
		
	})
 
 
 
 
 
 
 
 
 
 
 
 
 
 
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
 function ivaCheck(){
		var iva=$("#iva");
		var ivaReg=/^[0-9]{11}$/;
     let error=iva.next();
    
		if(iva.val()!="           " && !ivaReg.test(iva.val())){	//Iva non corretta
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
 /*
$(function() {
	var i=$('i');
	i.attr("title","modifica");
	i.click(function() {
		if(event.target.id=="cambiaPass"){
			var vecchia=prompt("Inserisci la tua vecchia password");
			var xhr = new XMLHttpRequest();
			xhr.open('GET', '../UserControl?action=vecchiaPassword&vecchia='+vecchia);
			xhr.send();
			 
		}
		else{
		$(this).prev().attr("disabled",false);
		$(this).prev().focus();}
	});
	
	$("input[type=text]").focusout(function() {
		if(confirm("Vuoi salvare le modifiche?")){
			var xhr = new XMLHttpRequest();
			xhr.open('GET', '../UserControl?action=change&cosa='+$(this).attr("name")+'&valore='+$(this).val(), true);		
			xhr.send();
		}
	})
})*/