<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="CSS/header.css" type="text/css">
<meta charset="UTF-8">


</head>

<body>

	<div id="header">
		<nav  class="header" >
			<ul>
				<li><a href="<%=response.encodeURL("TournamentControl?action=create")%>">CreaTorneo</a></li>
				<li><a href="#">Lista Tornei</a></li>
				<li><a href="#">Elenco Squadre</a></li>
				<li><a href="#">Elenco giocatori</a></li>
				<li><a href="#">About</a></li>			
			</ul>	
		</nav>
			
		</div>

</body>
</html>