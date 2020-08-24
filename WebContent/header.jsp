<%@page import="it.unisa.model.utente.UtenteBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()+"/CSS/header.css"%>" type="text/css">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<%UtenteBean utente=(UtenteBean)session.getAttribute("user");%>
</head>

<body>
	<nav>
	<input type="checkbox" id="check">
	<label for="check" class="checkbtn">
	<i class="fa fa-bars" aria-hidden="true"></i>
	</label>
	<label class="logo">MONTEFUSCOMERDA</label>
	<ul>
	<li><a href="<%=request.getContextPath()+"/index.jsp"%>" class="active">HOME</a></li>
	<li><a href="<%=response.encodeURL(request.getContextPath()+"/user/FormCreazioneTorneo.jsp")%>">CREA TORNEO</a></li>
	<li><a href="#">CONTATTI</a></li>
	
	<%if(session.getAttribute("user")==null) {%>
  		<li><a href="<%=response.encodeURL(request.getContextPath()+"/FormLoginAndRegister.jsp")%>">ACCEDI O REGISTRATI</a></li>
  		<%} else{ 
  		UtenteBean user= (UtenteBean)session.getAttribute("user");
  		%>
  		<li><a href="LoginAndRegisterControl?action=logout" title="Cliccami per fare logout" id="login-register"  class="profile-image">
		  <img src="<%=user.getImg()%>" class="source"></a></li>
  		<%} %>
	</ul>
	
	</nav>

  		<a href="javascript:void(0);" class="icon" onclick="funzione()">
  		
    		
  		</a>



</body>
</html>