<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%UtenteBean u=(UtenteBean)session.getAttribute("user"); %>
	<meta charset="UTF-8">	

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="../Script/messaggi.js"></script>
	<style>
		.mess{
			cursor:pointer;
		}
		.containerMessaggi{
			border-style:solid;
			width:25%; 
			margin-right:10px;
			margin-left:10px;
		}
		.viewerMessaggi{
			border-style:solid;
			width: 50%;
			margin-left:10px;
			padding-bottom:100px;
		}
		.messaggi{
			display:flex;
			flex-wrap:wrap;
		}
		.nomeTecnico{
			font-size:22px;
			margin:5px 0px 10px 10px;
			border-style: solid;
			width:25%;
		}
		i{
			margin-left:30px;
		}
	</style>
</head>
<body>
	<%@ include file="../header.jsp"%>
		<input type="hidden" value="<%=request.getSession().getId()%>" id="session">
	

	<div class="nomeTecnico"> <%=u.getUsername() %></div>
	
	<div class="messaggi">
		<div class="containerMessaggi"></div>
		<div class="viewerMessaggi"> <p>Seleziona un messaggio per visualizzarlo</p></div>
	</div>
</body>
</html>