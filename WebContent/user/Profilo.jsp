<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

	<head>
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
     <link rel="stylesheet" href="../CSS/Profilo.css" type="text/css">
     <script src = "<%=request.getContextPath()+"/Script/profilo.js"%>"></script>
     <%
String error=" ";
String type=" ";
String errorLocation=" ";
if(session.getAttribute("error")!=null && session.getAttribute("error-type")!=null && session.getAttribute("error-location")!=null){
	error = (String)session.getAttribute("error");
	type= (String)session.getAttribute("error-type");	
	errorLocation =(String)session.getAttribute("error-location");
}

System.out.println("location "+errorLocation);
System.out.println("error "+error);
System.out.println("type "+type);


%>
	</head>	
<body>
		<%@ include file="../header.jsp"%>	
	
	
			
		<div class=tuttecose>
			<table>
			<tr>
			<th>
				<div class=immagine>
					<img src="<%=utente.getImg() %>" class="profile-picture">
				</div>
			</th>
			
			<th>
			<!-- Dati dell'utente modificabili -->
		<div class="dati">
		
			<div class="field">
				<label for="Nome" class="testlabel">Username</label>
				<input type="text" id="username"   value="<%=utente.getUsername() %>" name="username">
				<span class="error"><%if(type.equals("username")){ %><%=error%><%}%></span>
				
			</div>
			
			
				<div class="field">
					<label for="mail" class="testlabel">E-Mail</label>
					<input type="text" id="email"  value="<%=utente.getEmail() %>" name="email">
				<span class="error"><%if(type.equals("email")){ %><%=error%><%}%></span>
				</div>
			
				<div class="field">
					<label for="iva" class="testlabel">Partita IVA</label>
					<input type="text" id="iva"   value="<%=utente.getpIVA()%>" name="pIVA">
					<span class="error"></span>
				</div>
			<input type="button" class="button-blue" value="Conferma le modifiche" onclick="confermaMod()">	
		</div>
		
		
		<div class="passWord">
				<div class="field">
					<label for="mail" class="testlabel">Vecchia Password</label>
					<input type="text" id="vecchia" name="vecchiaPsw">
					
				</div>
				<div class="field">
					<label for="mail" class="testlabel">Password</label>
					<input type="text" id="nuova"  name="nuovaPsw">
					
			</div>
		</div>
			
			
   			
			
			</th>
			</tr>
			<tr>
			<th>
				
			</th>
			</tr>
			<tr>
			<th>
			<div class="field">
				<div class="form-group file-area">
        		<label for="images" class="testlabel">Cambia immagine <br><span> La tua immagine deve essere 150x150</span></label>
   			 	<input type="file" name="images" id="images-0" required="required" />
   				</div>
   			</div>
   			</th>
			</tr>
			</table>
			<h1>I TUOI TORNEI</h1>
			<div id="mieiTornei"></div>
		</div>
		
		
		
		<input type="hidden" id="UtenteName" value="<%=utente.getUsername() %>">
		<input type="hidden" id="UtenteEmail" value="<%=utente.getEmail() %>">
		<input type="hidden" id="UtenteIVA" value="<%=utente.getpIVA()%>">
		
			
</body>
</html>

