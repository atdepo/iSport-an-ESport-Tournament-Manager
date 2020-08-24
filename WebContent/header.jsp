<%@page import="it.unisa.model.utente.UtenteBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%UtenteBean utente=(UtenteBean)session.getAttribute("user");
		String userImg=null;
	if(utente!=null){
		userImg=utente.getImg();
	}
%>    
    
<!DOCTYPE html>
<html>
<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src = "Script/header.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()+"/CSS/header.css"%>" type="text/css">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">



</head>

<body>
<input type="hidden" id="user-img" value ="<%=userImg%>">
	<nav>
	<input type="checkbox" id="check" onclick="toggle()">
	<label for="check" class="checkbtn">
	<i class="fa fa-bars" aria-hidden="true"></i>
	</label>
	<label class="logo">MONTEFUSCOMERDA</label>
	<ul id="link">
	<li><a href="<%=request.getContextPath()+"/index.jsp"%>" class="active">HOME</a></li>
	<li><a href="<%=response.encodeURL(request.getContextPath()+"/user/FormCreazioneTorneo.jsp")%>">CREA TORNEO</a></li>
	<li><a href="#">CONTATTI</a></li>
	
		<!--<%if(utente==null) {%>
  			<li><a href="<%=response.encodeURL(request.getContextPath()+"/FormLoginAndRegister.jsp")%>">ACCEDI O REGISTRATI</a></li>
  		<%} else{%>
		  <li>
			  <div class="div-profile-image">
			  	  <a href=""> 
			  		<img src="<%=userImg%>" class="source">
			  	</a>
			  </div>
		  </li>
  		<%} %>-->
  	<li>	
  	<div class="avatar-dropdown-menu">
      <div class="avatar-image"><img src="<%=userImg%>" ></div>
      <div class="avatar-dropdown-menu-items">
        <ul>
          <li>
            <a>My account</a>
          </li>
          <li>
            <a>Settings</a>
          </li>
          <li>
            <a>Log out</a>
          </li>
        </ul>
      </div>
    </div>
    </li>


	</ul>
	
	</nav>

  		<a href="javascript:void(0);" class="icon" onclick="funzione()">
  		
    		
  		</a>



</body>
</html>