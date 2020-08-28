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
				<label for="nomeSquadra" class="testlabel">Nome</label>
				<input type="text" class=" nome-torneo" placeholder="Inserisci il nome della Squadra" name="nomeSquadra">
			</div>	
			<div class="field">
				<label for="nazionalita" class="testlabel">Nazionalità</label>
				<input type="text" class=" nome-torneo" placeholder="Inserisci la nazionalità" name="nazionalita">
			</div>
			<div class="field">
				<div class="form-group file-area">
        			<label for="images" class="testlabel">Immagine Squadra<span> La tua immagine deve essere 150x150</span></label>
   				 	<input type="file" name="images" id="images-0" required="required" />
  				 <div class="file-dummy">
     			 <div class="success">Hai inserito un immagine BRAV</div>
     			 <div class="default">Favorire documento,grazie!</div>
    				</div>
  				</div>
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