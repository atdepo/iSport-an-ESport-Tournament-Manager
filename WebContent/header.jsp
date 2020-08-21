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

<%UtenteBean utente=(UtenteBean)session.getAttribute("user");%>
</head>
<meta charset="UTF-8">
<body>
	<div class="nav" id="navigationBar">
  		<a href="<%=request.getContextPath()+"/index.jsp"%>" class="active">Home</a>
  		<a href="<%=response.encodeURL(request.getContextPath()+"/user/FormCreazioneTorneo.jsp")%>">Crea Torneo</a>
  		<a href="#">Elenco Squadre</a>
  		<a href="#">Elenco giocatori</a>
  		<%if(session.getAttribute("user")==null) {%>
  		<a href="<%=response.encodeURL(request.getContextPath()+"/FormLoginAndRegister.jsp")%>" id="login-register">Login/register</a>
  		<%} else{ 
			  UtenteBean user= (UtenteBean)session.getAttribute("user");
		%>
		
  		<a href="LoginAndRegisterControl?action=logout" title="Cliccami per fare logout" id="login-register"  class="profile-image">
		  <img src="<%=user.getImg()%>" class="source"></a>
  		<%} %>
  		
  		<a href="javascript:void(0);" class="icon" onclick="nav()">
  		
    		<i class="fa fa-bars" aria-hidden="true"></i>
  		</a>
	</div>
	
	<script>
	function nav(){
	  	var x = document.getElementById("navigationBar");
	  	if (x.className === "nav") {
	    	x.className += " responsive";
	  	} else {
	    	x.className = "nav";
	  	}
	}
	</script>

</body>
</html>