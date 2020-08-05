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


<style type="text/css">
	body{background-color:#070909}
	
	label,h2,legend{color:lime}
	
	form{margin:10%}
	


</style>
<link rel="stylesheet" href="CSS/CreazioneTorneo.css" type="text/css">
<link rel="stylesheet" href="CSS/2RadioButton.css" type="text/css">
<meta charset="UTF-8">

<title >Crea il tuo torneo</title>

      <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	  <script src = "Script/mode.js"></script>
</head>

<body>
<%@ include file="header.jsp"%>
	<div class="title">
	<h2 >CREA ORA IL TUO TORNEO</h2>
	</div>
	<form name="FormCreazioneTorneo" action="<%=response.encodeURL("TournamentControl?action=validate")%>" method="post">
		<fieldset>
			<legend>Informazioni generali</legend>
			<label>Nome del Torneo <input type="text" name="nometorneo" required></label> <br> <br> <br>
			<label>Data Torneo <input type="date" name="datatorneo" required></label> <br> <br> <br>
			<label>Gioco di riferimento
				<select name="gioco" id="gioco" onchange="getMode()"></select>
				
			</label> <br> <br>
			<label>Modalita di gioco <select id="mode" name="modalita"></select>
			</label> <br> <br>
			
			<fieldset>
				<legend >Come si svolgerà il torneo</legend>
				<div class="contieniBottoni" >
				<br>
				<input id="on-line" type="radio" name="sel" value="on-line" checked class="sel bt1">
				<label class="radioLabel" for="on-line">On-line</label>	
				
				<input id="fisico" type="radio" name="sel" value="fisico" class="sel bt2">
				<label class="radioLabel" for="fisico">Fisico </label>
				<br><br>
				
				</div>
			</fieldset>
			<br> <br> <br> <label>Struttura
			
			<select name="strutture" id="strutture"></select>
			</label> <br> <br> <br> 
			
			<label>Budget stimato per il torneo(in euro iva esclusa) <input type="number" min="0" max=10000000 name="budget" required>
			</label><br><br><br>
			
			<label>Numero di tecnici desiderati <input type="number" min="0" name="tot_tecnici" id="tot_tecnici" onchange="numTecnici()" required></label> <br><br><br>
			
			<label>Di cui presenti fisicamente <input type="number" min="0"  max="10" name="tecnici_fisici" id="tecnici_fisici" required></label> <br><br><br>
			<!-- Il max utilizzato nei tecnici fisici è solo un placeholder per evitare che la barra diventi troppo grande -->
			
		</fieldset>
		<%if(error!=null) {%>
		<h3 style="color: red;" class="error">ERRORE: <%=error%></h3>
		<%} %>
		<input type="submit" class="formsub" value="Next">

	</form>
	 
</body>
</html>