/**
 * 
 */

	error=$(".error");
	error.hide();
   
  //CONTROLL EMAIL
  
    var email=$("#email")
    mailCheck(function() {
        
    var emailReg=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
   
    if(!email.val())	//Email non inserita
    	
    	email.next().show().text("Inserisci un'e-mail, non ti posso inviare spam altrimenti");
   
    else
    
    	if(!emailReg.test(email.val()))	//Email non corretta
      { 
    	  email.next().show().text("Inserisci un'e-mail credibile dai");
          $(this).addClass("er");
      }
     
    	else	//Tutt a post
    	  {
    	  	error.hide();
    	  	console.log("ok");
    	  }
    })
var username=$("#username")
    userCheck(function() {
        
    var usernameReg=/^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$/;
   
    if(!username.val())	//Username non inserita
    	username.next().show().text("Devi mettere uno username prova xxSpinnerKillerxx");
    else
      if(!usernameReg.test(username.val()))	//Username non corretta
      { 
         
    	  username.next().show().text("Magari qualcosa di decente");
          $(this).addClass("er");
      }
      else	//Tutt a post
    	  {
    	  	error.hide();
    	  	console.log("ok");
    	  }
    })
    
var password=$("#password")
    passCheck(function() {
        
    var passwordReg=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
   
    if(!password.val())	//Password non inserita
    	password.next().show().text("Devi mettere una password prova Kekko2000!");
    else
      if(!passwordReg.test(username.val()))	//Password non corretta
      { 
         
    	  password.next().show().text("Deve essere almeno 8 caratteri con almeno:un carattere speciale,un lowercase,un UPPERCASE e un numero ");
          $(this).addClass("er");
      }
      else	//Tutt a post
    	  {
    	  	error.hide();
    	  	console.log("ok");
    	  }
    })

var iva=$("#iva")
    ivaCheck(function() {
        
    var ivaReg=/^[0-9]{11}$/;
   
    if(!iva.val())	//Iva non inserita
    	iva.next().show().text("Devi inserire la tua IVA");
    else
      if(!ivaReg.test(username.val()))	//Password non corretta
      { 
         
    	  iva.next().show().text("Iva non corretta");
          $(this).addClass("er");
      }
      else	//Tutt a post
    	  {
    	  	error.hide();
    	  	console.log("ok");
    	  }
    })
    fullCheck(function(){
    	mailCheck();
    	userCheck();
    	passCheck();
    	ivaCheck();
    })
    
    
    var form=signup-$("signup-form");
    	form.submit(fullCheck);
    
    