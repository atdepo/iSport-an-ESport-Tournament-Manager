<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	String codice=(String)session.getAttribute("cod");
	System.out.println(codice);
	
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="Script/torneo.js"></script>
	
	<style type="text/css">
		.loghi{
			display:flex;
			flex-wrap: wrap;
			margin-top:24px;
		}
		.loghi > *{	
			margin:5px 10px 10px;
		}
		
		.nomiTeam > *{
			margin-right:5px;
		}
		h2{
			text-align:center;
		}
		h3{
				
		}
		body{
			color:#0082e6;
			background:url(img/black-gradient.png) repeat;
		}
	</style>	
	
	
</head>
<body>
		<%@ include file="header.jsp"%>
	<div class="tutto">
		<div class="nomeT"></div>
		
		<div class="nomeS"></div>
	
			<h2>Loghi delle squadre partecipanti</h2>
		<div class="loghi"></div>

	</div>
</body>
	<input type="hidden" value="<%=codice%>" id="torneo">
</html>









