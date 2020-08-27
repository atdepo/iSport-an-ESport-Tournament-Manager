<%@page import="it.unisa.model.gioco.GiocoBean"%>
<%@page import="it.unisa.model.struttura.StrutturaBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="it.unisa.model.struttura.StrutturaModel"%>
<%@page import="it.unisa.model.torneo.TournamentModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String error = null;
	String error_type=null;
	if(session.getAttribute("error")!=null && session.getAttribute("error-type")!=null){
		error=(String)session.getAttribute("error");
		error_type=(String) session.getAttribute("error-type");
		System.out.println(error);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../CSS/button-input.css" type="text/css">
<link rel="stylesheet" href="../CSS/Tendina.css" type="text/css">
<link rel="stylesheet" href="../CSS/CreazioneTorneo.css" type="text/css">
<link rel="stylesheet" href="../CSS/2RadioButton.css" type="text/css">

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
		<header>Informazioni di base:</header>
		<div class="form-div">
		
			<div class="progress-bar-wrapper">
				<div class="container-fluid">
				  
				  <ul class="list-unstyled multi-steps">
				    <li id="first-step" class="is-active">Informazioni di base</li>
				    <li id="second-step" class="">Scegli gioco e modalità</li>
				    <li id="third-step" class="">Crea il tuo torneo!</li>
				    
				  </ul>
				</div>
			</div>
			
			<form id="insTorneo" action="<%=request.getContextPath()+"/TournamentControl?action=validateTorneo"%>" method="post">
			
		<div class="page slidepage">
			<div class="field">
				<label for="nometorneo">Qual'è il nome del torneo da creare?</label>
				<input type="text" class="feedback-input nome-torneo" name="nometorneo">
			</div>	

			<div class="field">
				 <label for="number-container">Qual'è il budget stimato per il torneo?</label>
				
				<div class="number-container budget" title="Premi shift cliccando i selettori per avanzare di +/- 100 e premi CTRL sx per avanzare di +/- 1000">
					<span class="next budget"></span>
					<span class="prev budget"></span>
					<div class="box-span">
						<span class="number-box-budget">0 €</span>
					</div>
				
				</div>
			</div>
			
				<label for="homePage">Vuoi che il torneo sia mostrato in home page?<br><small style="color:red">(Questa opzione aggiungerà un costo di 250€ per ogni squadra partecipante)</small><br></label>
				
				<input id="toggle-on" class="toggle toggle-left" name="toggle" value="false" type="radio" checked>
				<label for="toggle-on" class="btn">Si</label>
				<input id="toggle-off" class="toggle toggle-right" name="toggle" value="true" type="radio">
				<label for="toggle-off" class="btn">No</label>
			
			
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
			<div class="title"></div>

			<div class="field">
				<label for="gioco">Seleziona il videogioco sul quale preferisci incentrare il tuo torneo e crea sfide epiche!
				<br><small style="color:red">Scegli bene perchè non potrai cambiarlo in seguito</small></label>	
				
					<div class="container gioco">
						<div class="select-box">
							<div class="options-container" id="gioco"></div>
							<div class="selected gioco" onclick="menu('gioco')">Gioco di riferimento</div>
						</div>
					</div>
			</div>

			<div class="field">
				<label for="mod">Seleziona la modalità nella quale le squadre si affronteranno!</label>
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
			
				<label for="radio">Come vuoi che sia organizzato il tuo torneo?</label><br>
					
				<input id="toggle-online" class="toggle toggle-left" name="r-button" value="false" type="radio" onclick="hide()" checked>
				<label for="toggle-online" class="btn">Online</label>
				<input id="toggle-offline" class="toggle toggle-right" name="r-button" value="true" type="radio" onclick="show()">
				<label for="toggle-offline" class="btn">Fisico</label>
			
			<div class="field">
				<label for="datatorneo">Che giorno vuoi svolgere il torneo?</label>
				<input type="date" class="feedback-input data-torneo" name="datatorneo">
				<br><span class="error-data" style="color:red"></span>
			</div>
			
				<input type="hidden" class="data-error-message" value="<%=error%>">
				<input type="hidden" class="struttura-error-message" value="<%=error%>">
			
			
			<div class="field">
				<div class="strutture"></div>
				<span class="error-struttura"></span>
				<!-- Da generare dinamicamente se il torneo è fisico -->
			</div>
			
			<div class="field">
				 <label for="tecnici">Quanti tecnici desideri avere per il tuo torneo?</label>
				
				<div class="number-container tecnici" title="Premi shift cliccando i selettori per avanzare di +/- 100 e premi CTRL sx per avanzare di +/- 1000">
					<span class="next tecnici"></span>
					<span class="prev tecnici"></span>
					<div class="box-span">
					<span class="number-box-tecnici">0</span>
				</div>
				
				<input type="hidden" class="tot-tecnici">
				<input type="hidden" class="max-tecnici-fisici">
				</div>
			</div>
	
				<div class="field">
					<div class="tecniciFisici"></div>
					
					<!-- Da generare dinamicamente se il torneo è fisico -->
					
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