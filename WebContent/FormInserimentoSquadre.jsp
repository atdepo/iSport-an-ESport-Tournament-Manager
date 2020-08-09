<%@page import="it.unisa.model.modalita.ModalitaModel"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Montefusco pupù</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="Script/giocatori.js"></script>
<link rel="stylesheet" href="CSS/giocatori.css" type="text/css">
<link rel="stylesheet" href="CSS/Tendina.css" type="text/css">
<link rel="stylesheet" href="CSS/2RadioButton.css" type="text/css">

</head>

<style>

	h3{background-color: purple; 
			text-align: center;}

</style>	
</head>

<body>
	<%@ include file="header.jsp"%>
	<div class="squadra">
		<h3>Montefusco merda</h3><br>
		<%=session.getAttribute("modalita") %>
		
		<div class="papino">
		<div class="contieniBottoni">
			
			<input type="radio" id="esistente" class="sel bt1" value="Aggiungi squadra esistente" name="sel">
			<label class="radioLabel" for="esistente">Esistente</label>
			<input type="radio" id="nuova" class="sel bt2" value="Aggiungi una nuova squadra" name="sel">
			<label class="radioLabel" for="nuova">Nuova</label>
			</div>
			<div class="squadreEsistenti"></div>
			<input type="button" value="Aggiungi una nuova squadra" id="aggiungi" onclick="add()">
		</div>
		</div>
		



</body>
</html>