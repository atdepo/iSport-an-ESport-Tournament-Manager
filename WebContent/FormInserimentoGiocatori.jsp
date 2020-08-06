<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Montefusco pupù</title>
<script src="Script/giocatori.js"></script>
<link rel="stylesheet" href="CSS/giocatori.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>

<body>
	<%@ include file="header.jsp"%>

	
	<div class="container">
		<div class="squadra1" align="left">
			<h2>Squadra 1</h2>
			<br><br>
			<input type="text" name="nomeSquadra1" placeholder="Montefusco">
		</div>

	
		
		<input type="button" value="aggiungi" onclick="add()">
		 
	</div>

</body>
</html>