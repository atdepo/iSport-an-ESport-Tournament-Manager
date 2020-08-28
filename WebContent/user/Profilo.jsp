<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<table>
			<tr>
			<th>
				<div class=immagine>
					<img src="<%=utente.getImg() %>" class="profile-picture">
				</div>
			</th>
			
			<th>
		<div class="dati">
		
		<label for="Nome" class="testlabel">Username</label>
				<input type="text" class=" nome" disabled value="<%=utente.getUsername() %>" name="nome">
			
			<div class="field">
				<label for="mail" class="testlabel">E-Mail</label>
				<input type="text" class="mail" disabled value="<%=utente.getEmail() %>" name="mail">
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
		</div>	
</body>
</html>

