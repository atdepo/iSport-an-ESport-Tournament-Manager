<%@page import="it.unisa.model.utente.UtenteBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!--<link rel="stylesheet" href="<%=request.getContextPath()+"/CSS/header.css"%>" type="text/css">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<meta charset="UTF-8">

<%UtenteBean utente=(UtenteBean)session.getAttribute("user");%>
-->
<!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <!-- Bootstrap core CSS -->
  <link href="<%=request.getContextPath()+"/CSS/bootstrap.min.css"%>" rel="stylesheet">
  <!-- Material Design Bootstrap -->
  <link href="<%=request.getContextPath()+"/CSS/mdb.min.css"%>" rel="stylesheet">
</head>
<meta charset="UTF-8">
<body><!-- 
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
 -->
<!-- .navbar -->
<nav class="navbar navbar-full navbar-dark bg-primary">
	<button class="navbar-toggler hidden-lg-up" type="button" data-toggle="collapse" data-target="#mainNavbarCollapse">
	&#9776;
	</button>
	<a class="navbar-brand" href="#">
	<img class="img-rounded" src="https://placehold.it/32/ffffff?text=B">
	</a>
	<div class="collapse navbar-toggleable-md" id="mainNavbarCollapse">
		<ul class="nav navbar-nav pull-lg-right">
			<li class="nav-item">
				<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">About</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">Blog</a>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link" id="navbarDropdown1" data-target="#" href="http://example.com" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
				<ul class="dropdown-menu" aria-labelledby="navbarDropdown1">
					<li><a class="dropdown-item" href="#">Action</a></li>
					<li><a class="dropdown-item" href="#">Another action</a></li>
					<li><a class="dropdown-item" href="#">Something else here</a></li>
				</ul>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">Contact</a>
			</li>
		</ul>
	</div>
</nav>
<!-- /.navbar -->






</body>
</html>