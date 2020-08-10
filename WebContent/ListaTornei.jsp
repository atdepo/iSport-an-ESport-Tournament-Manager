<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
	$(document).ready(function(){var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			var lista=$('.lista');
			lista.append('<ul>');
			for (var i = 0; i < data.length; i++) {
				lista.append('<li>'+data[i].nome+data[i].data+data[i].indirizzoStruttura+data[i].codGioco+data[i].CAPStruttura+data[i].codice+'</li>');
			}
			lista.append('</ul>');
			
		}
		alert('ciao');
		xhr.open('GET', 'TournamentControl?action=getTornei', true);
		xhr.send();
	}})
	</script>
</head>
	
<body>
	<%@ include file="header.jsp"%>
	
<div class="lista">
	
	
</div>

</body>
</html>