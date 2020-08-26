<%@page import="java.util.Enumeration"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>

<script>
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
if(urlParams.get('nomesquadra')===""||!urlParams.has('nomesquadra')){
	
	window.location.replace('FormInserimentoSquadre.jsp');
}
</script>


<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="../Script/insGiocatori.js"></script>
<script src="../Script/mode.js"></script>
<script src="../Script/button-input.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<meta charset="UTF-8">
<title>Inserimento Squadre</title>

<!--  <link rel="stylesheet" href="../CSS/giocatori.css" type="text/css">-->
<link rel="stylesheet" href="../CSS/Tendina.css" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Lato:100,200,300,400,500,600,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../CSS/button-input.css" type="text/css">
<link rel="stylesheet" href="../CSS/Tendina.css" type="text/css">
<link rel="stylesheet" href="../CSS/CreazioneTorneo.css" type="text/css">
<link rel="stylesheet" href="../CSS/2RadioButton.css" type="text/css">


</head>
<body>
	<%@ include file="../header.jsp"%>
	
	<input type="hidden" id="nPartecipanti" value=<%=(Integer)session.getAttribute("numPartecipanti")%>>
	
<div class="total-container">
		<header>Informazioni di base:</header>
		<div class="form-div">
		
			<div class="progress-bar-wrapper">
				<div class="container-fluid">
				  
				  <ul class="list-unstyled multi-steps">
				    <li id="first-step" class="is-active">Informazioni di base</li>
				    <li id="second-step" >Scegli gioco e modalità</li>
				    <li id="third-step" >Crea il tuo torneo!</li>
				    
				  </ul>
				</div>
			</div>
			
			<form action="<%=request.getContextPath()+"/TournamentControl?action=validateTorneo"%>" method="post">
			
		<div class="page slidepage">
			<div class="field">
				<label for="nickname">Nickname</label>
				<input type="text" class="feedback-input nome-torneo" placeholder="SuperMario64" name="nickname">
			</div>	
			
			<div class="field">
				<label for="nome">Nome</label>
				<input type="text" class="feedback-input nome-torneo" placeholder="Mario" name="nome">
			</div>
			
			<div class="field">
				<label for="cognome">Cognome</label>
				<input type="text" class="feedback-input nome-torneo" placeholder="Rossi" name="cognome">
			</div>	
			
			<div class="field">
				<label for="ruolo">Ruolo</label>
				<input type="text" class="feedback-input nome-torneo" placeholder="Jungler" name="ruolo">
			</div>
				
			<div class="field">
				<label for="nascita">Data di Nascita</label>
				<input type="date" class="feedback-input nome-torneo" name="nascita">
			</div>	
		
			<div class="field">
				<div class="form-group file-area">
        			<label for="images">Immagine<span>La tua immagine deve essere 150x150</span></label>
   				 	<input type="file" name="images" id="images" required="required" multiple="multiple"/>
  				 <div class="file-dummy">
     			 <div class="success">Hai inserito un immagine BRAV</div>
     			 <div class="default">Favorire documento,grazie!</div>
    				</div>
  				</div>
			</div>		


			<div class="field-btn">
				<input type="button" class="button-blue nextBtn1" onclick="slide()" value="Next">
			</div>
			
		</div>
			
				<div class="page">
				
			<div class="field-btn">
				<input type="button" class="button-blue prevBtn2" onclick="slide()" value="Previous">
				<input type="button" class="button-blue nextBtn2" onclick="slide()" value="Next">
			</div>

		</div>
		
		<div class="page">
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