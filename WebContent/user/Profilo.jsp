<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

	<head>
     <link rel="stylesheet" href="../CSS/Profilo.css" type="text/css">
	</head>	
<body>
		<%@ include file="../header.jsp"%>				
		<div class=tuttecose>
			<table>
			<tr>
			<th>
				<div class=Immagine>
					<img src="http://bit.ly/2tFWqOx" class="profile-picture">
				</div>
			</th>
			<th>
		<div class="dati">
		<label for="Nome" class="testlabel">Nome</label>
				<input type="text" class=" nome" disabled name="nome">
			
			<div class="field">
				<label for="cognome" class="testlabel">Cognome</label>
				<input type="text" class="cognome" disabled name="cognome">
			</div>
			
			<div class="field">
				<label for="mail" class="testlabel">Mail</label>
				<input type="text" class="mail" disabled name="mail">
			</div>
			
			<div class="field">
				<label for="iva" class="testlabel">Partita IVA</label>
				<input type="text" class="iva" disabled name="iva">
			</div>
			<div class="field">
				<div class="form-group file-area">
        		<label for="images" class="testlabel">Cambia immagine<span> La tua immagine deve essere 150x150</span></label>
   			 	<input type="file" name="images" id="images-0" required="required" />
   				</div>
   			</div>
			</div>
			</th>
			</tr>
			<tr>
			<th>
				<input type="text">
			</th>
			</tr>
			</table>
		</div>	
</body>
</html>

