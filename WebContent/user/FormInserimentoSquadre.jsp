<%@page import="it.unisa.model.modalita.ModalitaModel"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Montefusco pupù</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../Script/squadre.js"></script>

<link rel="stylesheet" href="../CSS/Tendina.css" type="text/css">
<link rel="stylesheet" href="../CSS/2RadioButton.css" type="text/css">

</head>

<style>
	body{
		color:#45A29E;
		background:url(../img/black-gradient.png) repeat;
	}
	
	h2{ 
		text-align: center;
	}		
	.flex-container{
		display:flex;
		flex-wrap:wrap;
		margin-top:20px;
	}	
	.flex-container > * {
		margin-left:10px;
		margine-right:10px;
		
	}
	input[type="button"]{
		margin-left:10px;
	}
	.contieniBottoni{
		margin-left:10px;
	}
}

</style>	
</head>

<body>
	<%@ include file="../header.jsp"%>
	<div class="squadra">
		<h2>Ora aggiungi le squadre per <%=session.getAttribute("nomeTorneo") %></h2><br>
		
		<div class="papino">
		<div class="contieniBottoni">
			
			<input type="radio" id="esistente" class="sel bt1" value="Aggiungi squadra esistente" name="sel" onchange="cambiaTipo()">
			<label class="radioLabel" for="esistente">Esistente</label>
			<input type="radio" id="nuova" checked class="sel bt2" value="Aggiungi una nuova squadra"  name="sel" onchange="cambiaTipo()">
			<label class="radioLabel" for="nuova">Nuova</label>
			</div>
			
			<div class="squadreEsistenti"></div>
			<input type="button" value="Aggiungi una nuova squadra" id="aggiungi" onclick="add()">

		</div>
		</div>
		
		<div id="listaSquadre"class="flex-container">
			
		
		
		</div>
		



</body>
</html>