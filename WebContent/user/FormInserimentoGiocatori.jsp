<%@page import="java.util.Enumeration"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String error=null;
	String errorpage=null;
	if(session.getAttribute("error")!=null && session.getAttribute("error-page")!=null){
		error=(String)session.getAttribute("error");
		errorpage=(String)session.getAttribute("error-page");
}
	
	System.out.println("Gli errori sono "+error+" "+errorpage);

%>

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
				<input type="text" class="feedback-input nome-squadra" placeholder="Inserisci il nome della Squadra" class="nome-squadra">
				<span class="error-name-0"></span>
			</div>	
			
			<div class="field">
				<label for="nazionalita" class="form-label">Nazionalità</label><!-- Qua ci va una tendina -->
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
		
		<input type="hidden" class="error" value="<%=error%>">
		<input type="hidden" class="error-page" value="<%=errorpage%>">
		
	</div>

</body>
</html> 