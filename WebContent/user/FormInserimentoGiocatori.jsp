<%@page import="java.util.Enumeration"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="../Script/insGiocatori.js"></script>
<script src="../Script/button-input.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<meta charset="UTF-8">
<title>Inserimento Squadre</title>
<link rel="stylesheet" href="../CSS/giocatori.css" type="text/css">


</head>
<body>
	<%@ include file="../header.jsp"%>	
<div class="total-container">
	<div class="form-div">
	<header>Creiamo la squadra</header>

		<div class="progress-bar-wrapper">
			<div class="container-fluid">
				<ul class="list-unstyled multi-steps">
				<li id="0-step" class="is-active">Squadra</li>
				</ul>
			</div>
		</div>
			
		<form action="#" method="post" id="the-form" enctype="multipart/form-data">
		
		<!--Pagina per la creazione della squadra, page 0 del multistep -->
		<div class="page page-0 slidepage">
			<div class="field">
				<label for="nome-squadra" class="form-label">Nome</label>
				<input type="text" class="feedback-input nome-squadra" placeholder="Inserisci il nome della Squadra" name="nome-squadra">
			</div>	
			
			<div class="field">
				<label for="nazionalita" class="form-label">Nazionalità</label>
				<input type="text" class="feedback-input nazionalita" placeholder="Inserisci la nazionalità" name="nazionalita">
			</div>
			
			<div class="field">
        		<label for="images-0" class="form-label">Immagine Squadra<br><small>La tua immagine deve essere massimo 150x150</small></label>
   				<input type="file" name="images" id="images-0" required="required"> 

			</div>	
			
			<div class="field-btn">
				<input type="button" class="button-blue nextBtn0" onclick="cambiaPagina()" value="Next">
				
			</div>
		</div>


		</form>
</div>
		
		
		
	</div>

</body>
</html> 