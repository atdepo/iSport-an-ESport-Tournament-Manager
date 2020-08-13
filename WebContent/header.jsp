<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="CSS/header.css" type="text/css">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<meta charset="UTF-8">


</head>
<meta charset="UTF-8">
<body>
	<div class="nav" id="navigationBar">
  		<a href="index.jsp" class="active">Home</a>
  		<a href="<%=response.encodeURL("FormCreazioneTorneo.jsp")%>">Crea Torneo</a>
  		<a href="#">Elenco Squadre</a>
  		<a href="#">Elenco giocatori</a>
  		<a href="javascript:void(0);" class="icon" onclick="funzione()">
    		<i class="fa fa-bars" aria-hidden="true"></i>
  		</a>
	</div>
	
	<script>
	function funzione() {
  	var x = document.getElementById("navigationBar");
  	if (x.className === "nav") {
    	x.className += " responsive";
  	} else {
    	x.className = "nav";
  	}
	}
	</script>

</body>
</html>