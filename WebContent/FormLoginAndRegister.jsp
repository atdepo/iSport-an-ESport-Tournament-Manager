<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
String error=" ";
String type=" ";
String errorLocation=" ";
if(session.getAttribute("error")!=null && session.getAttribute("error-type")!=null && session.getAttribute("error-location")!=null){
	error = (String)session.getAttribute("error");
	type= (String)session.getAttribute("error-type");	
	errorLocation =(String)session.getAttribute("error-location");
}

System.out.println(errorLocation);

%>
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
    
	 <%if(errorLocation.equals("login")||errorLocation.equals(" ")){%>
	 
    	<div id="login-form" style="display: block">
    	
    <%}else {%>
    
 	   <div id="login-form" style="display: none">
 	   
    <%} %>
    
        <form action="LoginAndRegisterServlet?action=validateLogin" method="post" class="log">
            <input type="email" name ="email" class="email" placeholder="Inserisci l'email"/>
            <input type="password" name="password" placeholder="Inserisci la password"/>
            <button type="submit" class="btn login">Accedi</button>
            <p><a href="javascript:void(0)">Password dimenticata</a> </p>
		          
        </form>
    </div>
	
    <%if(errorLocation.equals("signup")){%>
    
    	<div id="signup-form" style="display: block">
    	
    <%}else {%>
    
    	<div id="signup-form" style="display: none"><%} %>
        
        <form action="LoginAndRegisterServlet?action=register" method="post" class="sub" enctype="multipart/form-data">
            <input type="email" name="email" id="email" placeholder="E-mail"/>
            <span class="error"><%if(type.equals("email")){ %><%=error%><%}%></span>
            <input type="text" name="username" class="username" id="username" placeholder="Username"/>
            <span class="error"></span>
            <input type="text" name="pIva" class="pIva" id="iva" placeholder="Opzionale: Partita IVA">
            <span class="error"></span>
            <input type="password" name="password" class="password" id="password" placeholder="Password"/>
            <span class="error"></span>
            <input type="file" name="immagine" class="immagine" accept="image/*">
            <span class="error"></span>
            <input type="hidden" id="data" name="dataIscrizione">
            <button type="submit" class="btn signup">Crea account</button> 
                  
        </form>
    </div>

</div>


<!-- 
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
  
 -->
</body>
</html>
