<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%ArrayList<Integer> codici=(ArrayList<Integer>)session.getAttribute("commCazzVuoTu");
	if(codici==null){
		RequestDispatcher disp=request.getRequestDispatcher("IndexControl?action=TournamentHome");
		disp.forward(request, response);
	}
	
	%>
<!DOCTYPE html>
<html>
<head>
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="Script/index.js"></script>
	<link rel="stylesheet" href="CSS/index.css" type="text/css">
	<meta charset="UTF-8">
</head>

<body>
<%@ include file="header.jsp"%>

	
	
	<div class="intro-section" style="background-image: url('img/homeBackground.jpg');">
		<h1>Il Posto Perfetto per Competere</h1>
	</div>
	<div class=tornei>
		<label>Dai un occhiata a questi tornei!</label>
		<div id="slider">
 			<ul class="slider"> 
    		
    			<%for(Integer i:codici){ %>
    			<li id="<%=i.intValue()%>"><a href="UserControl?action=visualizza&codtorneo=<%=i.intValue()	%>"><img src="img/arena-<%=(i.intValue()%4)+1%>.jpg"></a> </li>
    			<%} %>
  			</ul>  
		</div>
		<div class="slider_option">
			<input type="checkbox" id="checkbox"> <!-- Autoplay -->
		</div> 
	</div>
	
<!-- Cards  -->
	<label class="how">Come funziona?</label>
	<div class="page-content">
  		<div class="card">
    		<div class="content">
      			<h2 class="title">Registrati</h2>
      				<p class="copy">Registrati al sito e inizia a competere subito</p> 
    		</div>
  		</div>
  		<div class="card">
    		<div class="content">
      			<h2 class="title">Crea il Tuo torneo</h2>
      			<p class="copy">Crea il tuo torneo personale, sia online che dal vivo</p>
    		</div>
  		</div>
  		<div class="card">
    		<div class="content">
      			<h2 class="title">Scegli le squadre</h2>
      			<p class="copy">Seleziona le squadre che parteciperanno al tuo torneo</p>
    		</div>
  		</div>
  		<div class="card">
    		<div class="content">
      			<h2 class="title">COMPETI!</h2>
      			<p class="copy">Ora le squadre possono comepetere come professionisti</p>
    		</div>
  		</div>
	</div>
        
        
	<%@ include file="Footer.jsp"%>
</body>
</html>