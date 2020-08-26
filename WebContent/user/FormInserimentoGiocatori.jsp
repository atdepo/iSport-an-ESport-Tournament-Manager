<%@page import="java.util.Enumeration"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%

if(session.getAttribute("numGiocatori")==null){
	System.out.println("chiamo la servlet su "+ request.getContextPath()+"/SquadreControl?action=getGiocatori");
	RequestDispatcher disp= request.getRequestDispatcher("../SquadreControl?action=getGiocatori");
	disp.forward(request, response);
}
	Integer tmp=(Integer)session.getAttribute("numGiocatori");
	System.out.println(tmp);
	int numeroGiocatoriSquadra= tmp.intValue();

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
	<input type="hidden" value="<%=numeroGiocatoriSquadra%>" id="numGiocatori">
<div class="total-container">
	<div class="form-div">
	<header>ASD</header>
		<div class="progress-bar-wrapper">
			<div class="container-fluid">
				<ul class="list-unstyled multi-steps">
				<%for(int j=1;j<=numeroGiocatoriSquadra;j++){%>
				
					<li id="<%=j%>-step" <%if(j==1){%>class="is-active"<%}%>>Giocatore <%=j%></li>
				<%}%>
				</ul>
			</div>
		</div>
			
		<form action="#" method="post" id="the-form" enctype="multipart/form-data">
		
		<%for(int i=1;i<=numeroGiocatoriSquadra;i++){%>
		
		<div class="page page-<%=i%> <%if(i==1){%>slidepage" style="display:block">
			<%}else{%>"style="display:none"> 
			<%}%>
			 		
			<div class="field">
				<label for="nickname" class="testlabel">Nickname</label>
				<input type="text" class="feedback-input nome-torneo" placeholder="SuperMario64" name="nickname">
			</div>	
			
			<div class="field">
				<label for="nome" class="testlabel">Nome</label>
				<input type="text" class="feedback-input nome-torneo" placeholder="Mario" name="nome">
			</div>
			
			<div class="field">
				<label for="cognome" class="testlabel">Cognome</label>
				<input type="text" class="feedback-input nome-torneo" placeholder="Rossi" name="cognome">
			</div>	
			
			<div class="field">
				<label for="ruolo" class="testlabel">Ruolo</label>
				<input type="text" class="feedback-input nome-torneo" placeholder="Jungler" name="ruolo">
			</div>
				
			<div class="field" class="testlabel">
				<label for="nascita" class="testlabel">Data di Nascita</label>
				<input type="date" class="feedback-input nome-torneo" name="nascita">
			</div>	
		
			<div class="field">
				<div class="form-group file-area">
        			<label for="images" class="testlabel">Immagine<span>La tua immagine deve essere 150x150</span></label>
   				 	<input type="file" name="images" id="images-<%=i%>" required="required" multiple="multiple"/>
  				 <div class="file-dummy">
     			 <div class="success">Hai inserito un immagine BRAV</div>
     			 <div class="default">Favorire documento,grazie!</div>
    				</div>
  				</div>
			</div>		
			<div class="field-btn">
				<%if(i>1){%>
					<input type="button" class="button-blue prevBtn<%=i%>" onclick="cambiaPagina()" value="Prev">
				<%}%>
				<input type="button" class="button-blue nextBtn<%=i%>" onclick="cambiaPagina()" value="Next">
				
			</div>
			
		</div>
		<%}%>
		
		
		</form>
</div>
		
		
		
	</div>

</body>
</html> 