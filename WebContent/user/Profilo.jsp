<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String error=" ";
	String type=" ";
	if(session.getAttribute("error")!=null && session.getAttribute("error-type")!=null){
		error = (String)session.getAttribute("error");
		type= (String)session.getAttribute("error-type");	
	
		System.out.println("error "+error);
		System.out.println("type "+type);
	}

%>
    
    
<!DOCTYPE html>

<html>

	<head>
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
     <link rel="stylesheet" href="../CSS/Profilo.css" type="text/css">
     <script src = "<%=request.getContextPath()+"/Script/profilo.js"%>"></script>

	</head>	
<body>
		<%@ include file="../header.jsp"%>	
	
	
			
		<div class=tuttecose>
			<div class="top">
				<div class="immagine">
					<img src="<%=utente.getImg() %>" class="profile-picture">
				</div>
			<!-- Dati dell'utente modificabili -->
				<div class="usernameP">
					<label class="nome"><%=utente.getUsername() %></label>
				</div>
			</div>
			
			
			<div class="field">
				<div class="form-group file-area">
        			<label for="images" class="testlabel">Cambia immagine <span class="misure"> La tua immagine deve essere 150x150</span></label>
   			 		<input type="file" name="images" id="images-0" required="required" />
   			 		<span class="error" id="errorImg"><%if(type.equals("img")){ %><%=error%><%}%></span>	
   				</div>
   			</div>
   			<div class="bottom">
				<div class="dati">
					<div class="field">
						<label for="username" class="testlabel">Username</label>
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
						<input type="text" id="iva" value="<%=utente.getpIVA()%>" name="pIVA">
						<span class="error"><%if(type.equals("piva")){ %><%=error%><%}%></span>
					</div>
					
					<input type="button" class="button-blue" value="Conferma le modifiche" onclick="confermaMod()">	


					<div class="passWord">
						<div class="field">
							<label for="mail" class="testlabel">Vecchia Password</label>
							<input type="text" id="vecchia" name="vecchiaPsw">
						</div>
						
						<div class="field">
							<label for="mail" class="testlabel">Password</label>
							<input type="text" id="nuova"  name="nuovaPsw">
							<input type="button" class="button-blue" value="Conferma le modifiche" onclick="cambiaPassword()">	
						</div>
					</div>
				</div>
		
				<div class="tornei">
   					<h1>I TUOI TORNEI</h1>
					<div id="mieiTornei">
					
					</div>
				</div>
			</div>
		</div>
		
		
		
		<input type="hidden" id="UtenteName" value="<%=utente.getUsername() %>">
		<input type="hidden" id="UtenteEmail" value="<%=utente.getEmail() %>">
		<input type="hidden" id="UtenteIVA" value="<%=utente.getpIVA()%>">
		<input type="hidden" id="UtenteImg" value="<%=utente.getImg()%>">
		
</body>
</html>

