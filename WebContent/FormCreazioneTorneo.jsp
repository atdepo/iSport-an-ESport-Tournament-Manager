<%@page import="it.unisa.model.gioco.GiocoBean"%>
<%@page import="it.unisa.model.struttura.StrutturaBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="it.unisa.model.struttura.StrutturaModel"%>
<%@page import="it.unisa.model.torneo.TournamentModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String error=(String)request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="CSS/CreazioneTorneo.css" type="text/css">
<link rel="stylesheet" href="CSS/2RadioButton.css" type="text/css">
<link rel="stylesheet" href="CSS/Tendina.css" type="text/css">
<meta charset="UTF-8">

<title>Crea il tuo Torneo</title>

      <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	  <script src = "Script/mode.js"></script>
</head>

<body>
<%@ include file="header.jsp"%>
	<div class="title">
	<h2>CREA ORA IL TUO TORNEO</h2>
	</div>
	<form name="FormCreazioneTorneo" action="<%=response.encodeURL("TournamentControl?action=giocatori")%>" method="post">
			<fieldset>
			
			<label>Nome del Torneo <input type="text" name="nometorneo" required></label> 
			<label>Data Torneo <input type="date" name="datatorneo" required></label> 
			
			<label for="gioco">Gioco di riferimento</label>
			<div class="container">
					<div class="select-box">
			        	<div class="options-container" id="gioco">
						</div>
			    		<div class="selected gioco" onclick="menu('gioco')" >
			              Gioco di riferimento
			    		</div>
			    	</div>
			</div>


			<label>Modalita di gioco</label> 
			
			<div class="container">
					<div class="select-box">
			        	<div class="options-container" id="mode">
						</div>
			    		<div class="selected mode" onclick="menu('mode')" >
			              Modalità di gioco
			              
			    		</div>
			    	</div>
			</div>
			
			</fieldset>
			<fieldset>
			
				<legend >Come si svolgerà il torneo</legend>
				<div class="contieniBottoni">
				<br>
				<input id="on-line" type="radio" name="sel" value="on-line" onclick="hide()" checked class="sel bt1">
				<label class="radioLabel" for="on-line">On-line</label>	
				
				<input id="fisico" type="radio" name="sel" value="fisico" onclick="show()"class="sel bt2">
				<label class="radioLabel" for="fisico">Fisico </label>
				<br><br>
				
				</div>
				
			
			<div class="strutture"></div>     <!-- Da generare dinamicamente se il torneo è fisico -->
			
			
			<label for="budget">Budget stimato per il torneo(in euro iva esclusa) <input type="number" min="0" max=10000000 name="budget" required>
			</label>
			
			<label for="tot_tecnici">Numero di tecnici desiderati</label> 
			<input type="number" min="0" name="tot_tecnici" id="tot_tecnici" onchange="numTecnici()" required>
			
			
			<div class="tecniciFisici"></div>     <!-- Da generare dinamicamente se il torneo è fisico -->
			
			
			<!-- Il max utilizzato nei tecnici fisici è solo un placeholder per evitare che la barra diventi troppo grande -->
			
		</fieldset>
		<%if(error!=null) {%>
		<h3 style="color: red;" class="error">ERRORE: <%=error%></h3>
		<%} %>
		<input type="submit" class="formsub" value="Next">

	</form>
	 
</body>
</html>