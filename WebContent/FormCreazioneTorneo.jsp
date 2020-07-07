<%@page import="it.unisa.model.struttura.StrutturaBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="it.unisa.model.struttura.StrutturaModel"%>
<%@page import="it.unisa.model.torneo.TournamentModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

Collection<?> strutture= (Collection<?>)request.getAttribute("strutture");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crea il tuo torneo</title>
</head>
<body>
<h2>CREA ORA IL TUO TORNEO</h2>
<form name="ILNOMEDELFORM" action="<%= response.encodeURL("TournamentControl?text")%>" method="post">
<fieldset>
	<legend>Informazioni generali</legend>
	
	<label>Nome del Torneo</label>  <input type="text">
	<br><br><br>
	<label>Data Torneo</label>  <input type="date">
	<br><br><br>
	<label>Gioco di riferimento</label>  <select></select>
	<br><br><br>
	
	<fieldset>
	<legend>Come si svolgerà il torneo</legend>
		<label>On-line</label> <input type="radio" name="sel" value="on-line"> <br><br><br>
		<label>Fisico</label> <input type="radio" name="sel" value="fisico"> <br><br><br>
	</fieldset>
	
</fieldset>

	<label>Struttura</label>
	<%Iterator<?> it=strutture.iterator(); %>
		<select>
			<%while(it.hasNext()){
				StrutturaBean bean= (StrutturaBean)it.next(); 
			%>
			
			<option><%=bean.getNome()%></option>
			
			<%} %>
		</select> 
	
	<br><br><br>




<input type="submit" value="press my ass">


</form>
</body>
</html>