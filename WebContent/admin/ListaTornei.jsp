<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
	
	$(document).ready(function(){
	
		var xhr = new XMLHttpRequest();
		$(".mimmo").append(xhr.readyState);
		xhr.onreadystatechange = function() {
			
			if (xhr.status == 200 && xhr.readyState == 4) {
				let data = JSON.parse(xhr.responseText);
				console.log(data);			
				var div=$(".mimmo");
				for (var i = 0; i < data.length; i++) {
					div.append('<p>'+data[i].nome+' <a href="../TournamentControl?action=deleteTorneo&cod='+data[i].codice+'"><i class="fas fa-dumpster-fire"></i></a><br>');	
		
				}
			
			}
		

	}
	xhr.open('GET', '../TournamentControl?action=getTornei', true);		
	xhr.send();
		})
	
	
	
	</script>
	<style type="text/css">
	.fa-dumpster-fire{color: red}
	</style>
</head>
	
<body>
	<%@ include file="../header.jsp"%>
	<div class="mimmo">



</div>
	
</body>
</html>