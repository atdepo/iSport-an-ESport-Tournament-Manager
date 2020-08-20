<%@page import="it.unisa.model.gioco.GiocoBean"%>
<%@page import="it.unisa.model.struttura.StrutturaBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="it.unisa.model.struttura.StrutturaModel"%>
<%@page import="it.unisa.model.torneo.TournamentModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../CSS/button-input.css" type="text/css">
<link rel="stylesheet" href="../CSS/2RadioButton.css" type="text/css">
<link rel="stylesheet" href="../CSS/Tendina.css" type="text/css">
<link rel="stylesheet" href="../CSS/CreazioneTorneo.css" type="text/css">

<meta charset="UTF-8">

<title>Crea il tuo Torneo</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../Script/mode.js"></script>
<script src="../Script/button-input.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>
	<%@ include file="../header.jsp"%>
	
	<div class="total-container">
		<header>Iniziamo con la creazione del tuo torneo!</header>
		<div class="form-div">
		
			<div class="progress-bar-wrapper">
				<div class="container-fluid">
				  
				  <ul class="list-unstyled multi-steps">
				    <li id="first-step" class="is-active fas fa-check">Start</li>
				    <li id="second-step" class="fas fa-check">Middle-Step</li>
				    <li id="third-step" class="fas fa-check">Finish</li>
				    
				  </ul>
				</div>
			</div>
			
			<form action="#" method="post">
		<div class="page slidepage">
			<div class="title">Informazioni di base:</div>

			<div class="field">
				<label for="nometorneo">Qual'è il nome del torneo da creare?</label>
				<input type="text" class="feedback-input nome-torneo" name="nometorneo" required>
			</div>	

			<div class="field">
				<label for="datatorneo">Che giorno vuoi svolgere il torneo?</label>
				<input type="date" class="feedback-input data-torneo" name="datatorneo" required>
			</div>

			<div class="field">
				 <label for="number-container">Qual'è il budget stimato per il torneo?</label>
				
				<div class="number-container budget" title="Premi shift cliccando i selettori per avanzare di +/- 100 e premi CTRL sx per avanzare di +/- 1000">
					<span class="next"></span>
					<span class="prev"></span>
					<div class="box-span">
						<span id="number-box">0 €</span>
					</div>
				
				</div>
			</div>

			<div class="field">
			<label for="contieniSponsor">Scegli degli sponsor</label>
				<div class="contieniSponsor">
					<ul class="ks-cboxtags">
					</ul>
				</div>
			</div>

			<div class="field-btn">
				<input type="button" class="button-blue nextBtn1" onclick="slide()" value="Next">
			</div>


		</div>
		
		<div class="page">
			<div class="title">Informazioni sul gioco:</div>

			<div class="field">
				<label for="gioco">Gioco di riferimento</label>	
					<div class="container gioco">
						<div class="select-box">
							<div class="options-container" id="gioco"></div>
							<div class="selected gioco" onclick="menu('gioco')">Gioco di riferimento</div>
						</div>
					</div>
			</div>

			<div class="field">
				<label for="mod">Modalita di gioco</label>
					<div class="container mod">
						<div class="select-box">
							<div class="options-container" id="mode"></div>
							<div class="selected mode" onclick="menu('mode')">Modalità di gioco</div>
						</div>
					</div>
			</div>

			<div class="field-btn">
				<input type="button" class="button-blue prevBtn2" onclick="slide()" value="Previous">
				<input type="button" class="button-blue nextBtn2" onclick="slide()" value="Next">
			</div>

		</div>
		
		<div class="page">
			<div class="title">Organizzazione:</div>

			
				<label for="radio">Come vuoi che sia organizzato il tuo torneo?</label>
					<div class="contieniBottoni radio">
					
						 <input id="on-line" type="radio" name="sel"
							value="on-line" onclick="hide()" checked class="sel bt1">
							
						<label class="radioLabel" for="on-line">On-line</label>
						 <input id="fisico" type="radio" name="sel" value="fisico"
								onclick="show()" class="sel bt2"> <label
								class="radioLabel" for="fisico">Fisico </label>
					</div>
			
			<div class="field">
				<div class="strutture"></div>
				<!-- Da generare dinamicamente se il torneo è fisico -->
			</div>
			
			<div class="tecnici">
				<div class="field">
					<label for="tot_tecnici">Quanti tecnici desideri avere per il tuo torneo?</label>
					<input type="number" min="0" name="tot_tecnici" id="tot_tecnici"
					class="feedback-input" onchange="numTecnici()" required>
				</div>
	
				<div class="field">
					<div class="tecniciFisici"></div>
					<!-- Da generare dinamicamente se il torneo è fisico -->
				</div>
				
			</div>
			<div class="field-btn">
				<input type="button" class="button-blue prevBtn3" onclick="slide()" value="Previous">
				<input type="submit" class="button-blue subBtn" value="Finish">
				
			</div>
		</div>
		</form>
		
		
	</div>
</div>
</body>
</html>