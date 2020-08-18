<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Login Form</title>
    <link rel="stylesheet" type="text/css" href="CSS/login.css">
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="Script/login-register.js"></script>
    
</head>
<body>
	<%@ include file="header.jsp"%>
	
  <div class="form-modal">
    
    <div class="form-toggle">
        <button id="login-toggle" onclick="toggleLogin()">Accedi</button>
        <button id="signup-toggle" onclick="toggleSignup()">Registrati</button>
    </div>

    <div id="login-form">
        <form action="LoginAndRegisterServlet?action=validateLogin" method="post">
            <input type="email" name ="email" placeholder="Inserisci l'email"/>
            <input type="password" name="password" placeholder="Inserisci la password"/>
            <button type="submit" class="btn login">Accedi</button>
            <p><a href="javascript:void(0)">Password dimenticata</a> </p>
		          
        </form>
    </div>

    <div id="signup-form" >
        <form action="LoginAndRegisterServlet?action=register" method="post" enctype="multipart/form-data">
            <input type="email" name="email" placeholder="E-mail" required/>
            <input type="text" name="username" placeholder="Username" required/>
            <input type="text" name="pIva" placeholder="Partita IVA">
            <input type="hidden" id="data" name="dataIscrizione">
            <input type="password" name="password" placeholder="Password" required/>
			<input type="file" name="immagine" accept="image/*">         
    		<button type="submit" class="btn signup">Crea account</button> 
            
        </form>
    </div>

</div>
<script type="text/javascript">
		$(document).ready(function(){
			var d = new Date();

			var month = d.getMonth()+1;
			var day = d.getDate();

			var output = d.getFullYear() + '/' +
			    (month<10 ? '0' : '') + month + '/' +
			    (day<10 ? '0' : '') + day;
			$('#data').val(output);
		})
	
	</script>
    
</body>
</html>
