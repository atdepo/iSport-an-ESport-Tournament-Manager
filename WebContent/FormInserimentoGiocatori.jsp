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
<link rel="stylesheet" href="CSS/2RadioButton.css" type="text/css">

</head>


<body>
	<%@ include file="header.jsp"%>

	<div class="container">
		<div class="squadra">
		<h3>Inserisci una nuova squadra</h3><br>
			<div class="contieniBottoni">
			
			<input type="radio" id="esistente" class="sel bt1" value="Aggiungi squadra esistente" name="sel">
			<label class="radioLabel" for="esistente">Esistente</label>
			<input type="radio" id="nuova" class="sel bt2" value="Aggiungi una nuova squadra" name="sel">
			<label class="radioLabel" for="nuova">Nuova</label>
			</div>
			
			<input type="button" value="Aggiungi Squadra" onclick="add()">
		</div>
	</div>

</body>
</html>