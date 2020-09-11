<%@page import="java.util.Enumeration"%>
<%@page import="it.unisa.model.modalita.ModalitaModel"%>
<%@page import="it.unisa.model.squadra.SquadraBean" %>

<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<% ArrayList<SquadraBean> team=(ArrayList<SquadraBean>)session.getAttribute("squadreTorneo");
		System.out.println("*******inizio squadre*******"+team);
		if(team!=null){
		for(SquadraBean b:team){
			System.out.println(b.getNome());
		}
		}
		System.out.println("*******fine squadre*******");

		%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../Script/squadre.js"></script>

<link rel="stylesheet" href="../CSS/Tendina.css" type="text/css">
<link rel="stylesheet" href="../CSS/2RadioButton.css" type="text/css">

</head>
<%UtenteBean u= (UtenteBean)session.getAttribute("user"); %>
<style>
	body{
		color:#0082e6;
		background:url(../img/black-gradient.png) repeat;
	}
	.contenitore{
		text-align:-webkit-center;
	}
	h2{ 
		text-align: -webkit-center;
		margin-top:10px;
	}
	label{
		font-size:20px;
	}		
	.flex-container{
		display:flex;
		flex-wrap:wrap;
		margin-top:20px;
	}	
	.flex-container > * {
		margin-left:10px;
		margine-right:10px;
		
	}
	/*input[type="button"]{
		margin-left:10px;
		height:25px;
	}*/
	.radioLabel{
		overflow:hidden;
	}
	
	.button-blue{
	font-family: 'Montserrat', Arial, Helvetica, sans-serif;
	width: 250px;
	height:50px;
	border: #0088FF solid 4px;
	border-radius: 11px;
	cursor:pointer;
	background-color: #5DEFB9;;
	color:white;
	font-size:14px;
	padding-bottom:13px;
	padding-top:10px;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
  	margin-right:9px;
  	font-weight:700;
  	outline:none;
}

.button-blue.finish{

float:right;
}

.button-blue:hover{
	color: #0493bd;
}
	
}

</style>	
<link rel="stylesheet" type="text/css" href="../CSS/2RadioButton.css">
</head>

<body>
	<%@ include file="../header.jsp"%>
	<div class="squadra">
		<h2>Ora aggiungi le squadre per <%=session.getAttribute("nomeTorneo")%></h2><br>
		
		<div class="contenitore">
		<div class="contieniBottoni">
			
			<input type="radio" id="esistente" class="sel toggle toggle-left bt1" value="Aggiungi squadra esistente" name="sel" onchange="cambiaTipo()">
			<label class="radioLabel btn" for="esistente">Esistente</label>
			<input type="radio" id="nuova" checked class="sel toggle toggle-right bt2" value="Aggiungi una nuova squadra"  name="sel" onchange="cambiaTipo()">
			<label class="radioLabel btn" for="nuova">Nuova</label>
			</div>
			
			<div class="squadreEsistenti"></div>
			<input type="button" class="button-blue addsq" value="Aggiungi una nuova squadra" id="aggiungi" onclick="add()">

		</div>
		</div>
			
		<input type="hidden" value=<%=u.getpIVA()%> id="iva" >
		
		<div id="listaSquadre"class="flex-container">
			<%if(team!=null){
				for(SquadraBean b:team){
			%>
					
			<div class="<%=b.getNome()%> team"><img onclick="eliminaSquadra('<%=b.getNome()%>')" src="<%=b.getTeamImage()%>"></div>
			<%}}%>
			
		
		</div>
		<input type="button" class="button-blue finish" name="next" value="COMPLETA LA CREAZIONE" onclick="finishTorneo()">


</body>
</html>