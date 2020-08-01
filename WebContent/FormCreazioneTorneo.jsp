<%@page import="it.unisa.model.gioco.GiocoBean"%>
<%@page import="it.unisa.model.struttura.StrutturaBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="it.unisa.model.struttura.StrutturaModel"%>
<%@page import="it.unisa.model.torneo.TournamentModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
Collection<?> strutture = (Collection<?>) session.getAttribute("strutture");
Collection<?> giochi = (Collection<?>) session.getAttribute("giochi");
Integer numTecnici = (Integer) session.getAttribute("numtecnici");
String error = (String) request.getAttribute("error"); 

if (strutture == null && giochi == null) {
	response.sendRedirect(response.encodeRedirectURL("./TournamentControl?action=create"));
	return;
	
}
%>
<!DOCTYPE html>
<html>
<head>


<style type="text/css">
	body{background-color :#1F2833}
	
	label,h2,legend{color:#45A29E}
	
	form{margin:10%}
	
	div.title{text-align:center;}
   
   .container{align-items:center; display:flex; }
	


.radioLabel {
  border: 3px solid #1a1a1a;
  display: inline-block;
  padding: 10px;
  position: relative;
  text-align: center;
 
  transition: background 600ms ease, color 600ms ease;
}

input[type="radio"].sel {
  display: none;
}
input[type="radio"].sel + label {
  cursor: pointer;
  min-width: 60px;
}
input[type="radio"].sel + label:hover {
  background: none;
  color: #1a1a1a;
}
input[type="radio"].sel + label:after {
  background: #1a1a1a;
  content: "";
  height: 100%;
  position: absolute;
  top: 0;
  -webkit-transition: left 200ms cubic-bezier(0.77, 0, 0.175, 1);
  transition: left 200ms cubic-bezier(0.77, 0, 0.175, 1);
  width: 100%;
  z-index: -1;
}
input[type="radio"].sel.bt1 + label {
  border-right: 0;
}
input[type="radio"].sel.bt1 + label:after {
  left: 100%;
}
input[type="radio"].sel.bt2 + label {
  margin-left: -5px;
}
input[type="radio"].sel.bt2 + label:after {
  left: -100%;
}
input[type="radio"].sel:checked + label {
  cursor: default;
  color: #fff;
  -webkit-transition: color 200ms;
  transition: color 200ms;
}
input[type="radio"].sel:checked + label:after {
  left: 0;
}

</style>

<meta charset="ISO-8859-1">

<title >Crea il tuo torneo</title>

      <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	  <script src = "Script/mode.js"></script>
</head>

<body>
<%@ include file="header.jsp"%>
	<div class="title">
	<h2 >CREA ORA IL TUO TORNEO</h2>
	</div>
	<form name="FormCreazioneTorneo" action="<%=response.encodeURL("TournamentControl?action=validate")%>" method=post>
		<fieldset>
			<legend>Informazioni generali</legend>
			<label>Nome del Torneo <input type="text" name="nometorneo" required></label> <br> <br> <br>
			<label>Data Torneo <input type="date" name="datatorneo" required></label> <br> <br> <br>
			<label>Gioco di riferimento
				<select name="gioco" id="gioco" onchange="getMode()">
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
			</label> <br> <br>
			<label>Modalita di gioco <select id="mode" name="modalita">
			
			<option></option>
			
			</select></label>
			

			<fieldset>
						<legend >Come si svolgerà il torneo</legend>
				<div class="container" >
				
				<input id="on-line" type="radio" name="sel" value="on-line" checked class="sel bt1">
				<label class="radioLabel" for="on-line">On-line</label>	
				
				<input id="fisico" type="radio" name="sel" value="fisico" class="sel bt2">
				<label class="radioLabel" for="fisico">Fisico </label>
				</div>
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

				<option><%=bean.getNome()+", "+bean.getIndirizzo()+" - "+bean.getCAP()%></option>

				<%
					}
				%>
			</select> </label> <br> <br> <br> 
			<label>Budget stimato per il torneo(in euro iva esclusa) <input type="number" min="0" max=10000000 name="budget" required>
			</label><br><br><br>
			<label>Numero di tecnici desiderati <input type="number" min="0" max=<%=numTecnici.intValue()%> name="tot_tecnici" required></label> <br><br><br>
			<label>Di cui presenti fisicamente <input type="number" min="0" max="4" name="tecnici_fisici" required></label> <br><br><br>
			<!--Da scegliere dinamicamente -->
		</fieldset>
		<% if(error!=null) { %>
		<h3 style="color: red;">ERRORE: <%=error%></h3>
		<%} %>
		<input type="submit" value="Next ->">

	</form>
	<script type="text/javascript">
	  function doAjax(){
		     url = "test.json";
		     if(window.XMLHttpRequest){
		        // most browsers have a builtin XMLHttpRequest object
		        httpRequest = new XMLHttpRequest();
		     } else if(window.ActiveXObject){
		    	 httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		     } else {
		    	 alert("AJAX not supported");
		        return;
		     }

		     // Set the name of the function that will process the response from the request.
		     httpRequest.onreadystatechange = AjaxCallback;

		     // This triggers the request for a document.
		     // The third parameter 'true' causes the routine to run asynchronously.
		     // Execution continues here and the onreadystatechange routine will be called'
		     // when the operation completes.
		     httpRequest.open ('GET', url, true);
		     httpRequest.send (null);
		  }

		  function AjaxCallback() {
		     // 4 means the full response has been received.
		     if(httpRequest.readyState == 4){
		        alert(httpRequest.responseText);
	  		    var x = document.getElementById('myDIV');
	  		    x.innerHTML = httpRequest.responseText;
	  		    if (x.style.display === 'none') {
	  		      x.style.display = 'block';
	  		    } else {
	  		      x.style.display = 'none';
	  		    }
		     }
		  }

	</script>
</body>
</html>