<%@page import="java.util.Enumeration"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>

<script>
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
if(urlParams.get('nomesquadra')===""||!urlParams.has('nomesquadra')){
	alert('Per aggiungere una nuova squadra bisogna inserire il nome della squadra');
	window.location.replace('FormInserimentoSquadre.jsp');
}
</script>


<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="Script/insGiocatori.js"></script>

<meta charset="UTF-8">
<title>MIMMO</title>
<link rel="stylesheet" href="CSS/giocatori.css" type="text/css">

</head>
<body>
	<%@ include file="header.jsp"%>
	
	<input type="hidden" id="nPartecipanti" value=<%=(Integer)session.getAttribute("numPartecipanti")%>>
	
	
	
		 <div class="form-group file-area">
      	  <label for="images">Immagine <span>La tua immagine deve essere al massimo 150x150px </span></label>
   			 <input type="file" name="image" id="image" required="required" />
   			 <div class="file-dummy">
    			  <div class="success">Il tuo file è stato caricato GGWP</div>
    			  <div class="default">Seleziona un immagine per il giocatore</div>
   			 </div>
  		</div>
  
		  <div class="form-group">
		    <button type="submit">Chiavt a paccr</button>
		  </div>
	


</body>
</html>