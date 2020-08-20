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
  
    <script src="Script/validation.js"></script>
    
  <style type="text/css"> .er{color: darkred;}
  							span{background-color: #008080; } </style>
    
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
            <input type="email" name ="email" class="email" placeholder="Inserisci l'email"/>
            <input type="password" name="password" placeholder="Inserisci la password"/>
            <button type="submit" class="btn login">Accedi</button>
            <p><a href="javascript:void(0)">Password dimenticata</a> </p>
		          
        </form>
    </div>

    <div id="signup-form" >
        <form action="LoginAndRegisterServlet?action=register" method="post" enctype="multipart/form-data">
            <input type="text" name="email" id="email" placeholder="E-mail" />
          	<span class="error">dasasd </span>
            <input type="text" name="username" class="username" placeholder="Username" />
            <input type="text" name="pIva" class="pIva" placeholder="Partita IVA">
            <input type="hidden" id="data"  name="dataIscrizione">
            <input type="password" name="password" class="password" placeholder="Password" />
			<input type="file" name="immagine" class="immagine" accept="image/*">         
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
  <script type="text/javascript" src="Script/validation.js"></script>
</html>
