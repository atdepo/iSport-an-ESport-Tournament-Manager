<%@page import="it.unisa.model.utente.UtenteBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%UtenteBean utente=(UtenteBean)session.getAttribute("user");
	String userpIVA=null;
	String userImg=null;
	if(utente!=null){
		userImg=utente.getImg();
		if(utente.getpIVA()!=null)
			userpIVA=utente.getpIVA();
	}
%>    
    
<!DOCTYPE html>
<html>
<head>
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src = "<%=request.getContextPath()+"/Script/header.js"%>"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()+"/CSS/header.css"%>" type="text/css">
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<script src="https://kit.fontawesome.com/9e043e54f0.js" crossorigin="anonymous"></script>
	<style type="text/css">.logo:hover{cursor:pointer;}</style>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>i-Sport</title>
</head>

<body>
<input type="hidden" id="user-img" value ="<%=userImg%>">
	<input type="hidden" id="user-pIVA" value="<%=userpIVA%>">
	<nav>
	<input type="checkbox" id="check" onclick="toggle()">
	<label for="check" class="checkbtn">
	<i class="fa fa-bars" aria-hidden="true"></i>
	</label>
	<a href="<%=request.getContextPath()+"/index.jsp"%>" ><label  class="logo">i-Sport</label></a>
	<ul id="link">
	<li><a href="<%=request.getContextPath()+"/index.jsp"%>" class="active">HOME</a></li>
	<li><a href="<%=response.encodeURL(request.getContextPath()+"/user/FormCreazioneTorneo.jsp")%>">CREA TORNEO</a></li>
	<li><a href="<%=request.getContextPath()+"/contatti.jsp"%>">CONTATTI</a></li>	
	
		
  	<%if(utente==null){%>
  	  <li><a href="<%=request.getContextPath()+"/FormLoginAndRegister.jsp"%>">ACCEDI O REGISTRATI</a></li>
  	
      <%}else{ %>
      
      	
      <li><div class="avatar-dropdown-menu">
      <div class="avatar-image"><img src="<%=userImg%>" class="source"></div>
      <div class="avatar-dropdown-menu-items">
        <ul id="test">
          <li>
            <%if(utente.getTipo()=="user"){ %>
            <a href="<%=request.getContextPath()+"/user/Profilo.jsp"%>"class="dropdown-item">IL MIO PROFILO</a>
             <%}else if(utente.getTipo()=="admin"){ %>
             <a href="<%=request.getContextPath()+"/admin/Admin.jsp"%>"class="dropdown-item">PANNELLO ADMIN</a>
             <%}else if(utente.getTipo()=="tecnico"){ %>
             <a href="<%=request.getContextPath()+"/tecnico/messaggi.jsp"%>"class="dropdown-item">MESSAGGI</a><%} %>
          </li>
          <li>
            <a href="<%=request.getContextPath()+"/Logout"%>" class="dropdown-item">LOG OUT</a>
          </li>
          
        </ul>
      </div>
      <%} %>
    </div>
    </li>


	</ul>
	
	</nav>



</body>
</html>