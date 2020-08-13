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
<link rel="stylesheet" href="CSS/2RadioButton.css" type="text/css">
<link rel="stylesheet" href="CSS/Tendina.css" type="text/css">
<link rel="stylesheet" href="CSS/CreazioneTorneo.css" type="text/css">

<meta charset="UTF-8">

<title>Crea il tuo Torneo</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="Script/mode.js"></script>
<script src="Script/radio.js"></script>

</head>

<body>
	<%@ include file="header.jsp"%>
	<div class="title">
		
	</div>
	<div id="form-main">
		<div id="form-div">
			<form name="FormCreazioneTorneo"
				action="<%=response.encodeURL("TournamentControl?action=validate")%>"
				class="form" id="form1" method="post">
					<div class="front">
                    <p>Iniziamo con la creazione del tuo torneo!</p> 
                    </div>
					<label for="nometorneo"  >Nome del Torneo <input type="text" class="feedback-input"
						name="nometorneo"required></label>
				
				 <label>Data Torneo <input type="date" class="feedback-input"
				 		name="datatorneo" required></label>
				 
				 
				 <label for="gioco">Gioco di riferimento</label>	
					<div class="container gioco">
						<div class="select-box">
							<div class="options-container" id="gioco"></div>
							<div class="selected gioco" onclick="menu('gioco')">
							Gioco di riferimento</div>
						</div>
					</div>


				<label for="mod">Modalita di gioco</label>

					<div class="container mod">
						<div class="select-box">
							<div class="options-container" id="mode"></div>
							<div class="selected mode" onclick="menu('mode')">
								Modalità di gioco</div>
						</div>
					</div>

			

					<label for="radio">Come vuoi che sia organizzato il tuo torneo?</label>
					<div class="contieniBottoni radio">
					
						 <input id="on-line" type="radio" name="sel"
							value="on-line" onclick="hide()" checked class="sel bt1">
							
						<label class="radioLabel" for="on-line">On-line</label>
						 <input id="fisico" type="radio" name="sel" value="fisico"
								onclick="show()" class="sel bt2"> <label
								class="radioLabel" for="fisico">Fisico </label>
					</div>
						
					<div class="strutture"></div>
					<!-- Da generare dinamicamente se il torneo è fisico -->


					<label for="budget">Qual'è il budget stimato per il torneo(in euro iva esclusa)
					<input type="number" class="feedback-input" min="0" max=10000000 name="budget" required>
					</label> 
					
					<label for="tot_tecnici">Quanti tecnici desideri avere per il tuo torneo?</label>
					<input type="number" min="0" name="tot_tecnici" id="tot_tecnici"
						   class="feedback-input" onchange="numTecnici()" required>


					<div class="tecniciFisici"></div>
					<!-- Da generare dinamicamente se il torneo è fisico -->


					<!-- Il max utilizzato nei tecnici fisici è solo un placeholder per evitare che la barra diventi troppo grande -->

				
				
				<div class="submit">
					<input type="submit" id="button-blue" value="Next">
					<div class="ease"></div>
				</div>
				
			</form>
		</div>
	</div>

</body>
</html>