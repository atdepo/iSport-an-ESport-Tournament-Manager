<%@page import="it.unisa.model.gioco.GiocoBean"%>
<%@page import="it.unisa.model.struttura.StrutturaBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="it.unisa.model.struttura.StrutturaModel"%>
<%@page import="it.unisa.model.torneo.TournamentModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
Collection<?> strutture = (Collection<?>) request.getAttribute("strutture");
Collection<?> giochi = (Collection<?>) request.getAttribute("giochi");
Integer numTecnici = (Integer) request.getAttribute("numtecnici");

if (strutture == null && giochi == null) {
	response.sendRedirect(response.encodeRedirectURL("./TournamentControl?action=create"));
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crea il tuo torneo</title>
</head>
<body>
	<h2>CREA ORA IL TUO TORNEO</h2>
	<form name="FormCreazioneTorneo" action="<%=response.encodeURL("TournamentControl?action=validate")%>" method="post">
		<fieldset>
			<legend>Informazioni generali</legend>

			<label>Nome del Torneo <input type="text" name="nometorneo" required></label> <br> <br> <br>
			<label>Data Torneo</label> <input type="date" name="datatorneo" required> <br> <br> <br>
			<label>Gioco di riferimento
				<select name="gioco">
					<%
						Iterator<?> it1 = giochi.iterator();
					%>
	
					<%
						while (it1.hasNext()) {
						GiocoBean bean = (GiocoBean) it1.next();
					%>
					<option><%=bean.getNomeGioco()%></option>
	
					<%
						}
					%>

				</select>
			</label> <br> <br> <br>

			<fieldset>
				<legend>Come si svolgerà il torneo</legend>
				<label>On-line<input type="radio" name="sel" value="on-line"></label> <br> <br> 
				<label>Fisico <input type="radio" name="sel" value="fisico"></label>
			</fieldset>
			<br> <br> <br> <label>Struttura
			<%
				Iterator<?> it = strutture.iterator();
			%>
			<select name="struttura">
				<%
					while (it.hasNext()) {
					StrutturaBean bean = (StrutturaBean) it.next();
				%>

				<option><%=bean.getNome()%></option>

				<%
					}
				%>
			</select> </label> <br> <br> <br> 
			<label>Budget stimato per il torneo(in euro iva esclusa) <input type="number" min="0" max=100000 name="budget" required>
			</label><br><br><br>
			<label>Numero di tecnici desiderati <input type="number" min="0" max=<%=numTecnici.intValue()%> name="tot_tecnici" required></label> <br><br><br>
			<label>Di cui presenti fisicamente <input type="number" min="0" max="4" name="tecnici_fisici" required></label> <br><br><br>
			<!--Da scegliere dinamicamente -->
		</fieldset>





		<input type="submit" value="press me">


	</form>
</body>
</html>