/**
 * 
 */




	var error=$(".error");
	error.hide();
   
  //CONTROLL EMAIL
  
    var email=$("#email")
    giacomo=(function() {
        
    var emailReg=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
   
    if(!email.val())	//Email non inserita
    	{
    		error.show().text("Inserisci un'e-mail, non ti posso inviare spam altrimenti");
    		return false;
    	}
    else
    
    	if(!emailReg.test(email.val()))	//Email non corretta
      { 
    	  email.next().show().text("Inserisci un'e-mail credibile dai");
          $(this).addClass("er");
          return false;
      }
     
    	else	//Tutt a post
    	  {
    	  	error.hide();
    	  	console.log("ok");
    	  	return true;
    	  }
    
    })


    email.focusout(giacomo);
   
   







/*

 function emailCheck(campo) {
    let regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    console.log(regex.test(campo.val()))
    return regex.test(campo.val());
}

function usernameCheck(campo) {
    let regex =  /^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$/
    return regex.test(campo.value);
}


function passwdCheck(campo) {
    let regex = /^\S{6,}$/;
    return regex.test(campo.value);
}

function ivaCheck(campo) {
    let regex =/^[0-9]{11}$/
    return regex.test(campo.value);
}

function dataCheck(campo) {
    if(campo.value!=null)
    	return  true;
    else 
    	return false;
}

function checkCampiFormLogin() {
    const form = document.getElementById("login-form")
    const email = form.email;
    const password = form.password;

    let ch1 = emailCheck(email);
    let ch2 = passwdCheck(password);

    console.log(ch1 && ch2)
    return (ch1 && ch2);
}

function checkCampiRegistrazione() {
    const form = document.getElementById("signup-form")
    const username = form.username;
    const pIva= form.pIva;
    const email = form.email;
    const password = form.password;
    const data=form.data;
   
    let ch1 = emailCheck(email);
    
    /*
   
    let ch2 = passwdCheck(password);
    let ch3 = usernameCheck(username);
    let ch4 = ivaCheck(pIva);
	let ch5 = dataCheck(data);


}*/