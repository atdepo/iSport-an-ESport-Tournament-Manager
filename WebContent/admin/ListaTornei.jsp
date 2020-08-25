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
		xhr.onreadystatechange = function() {

			if (xhr.status == 200 && xhr.readyState == 4) {
				let data = JSON.parse(xhr.responseText);
				console.log(data);
				
				var mimmo = $('.mimmo');
		
				for (var i = 0; i < data.length; i++) {
					
					           
				mimmo.append('<img src="'+data[i].img+'">');
					 
					
				}
			
			}
		}
			
		xhr.open('GET', 'LoginAndRegisterServlet?action=retrieve', true);
		xhr.send();

		
		
	})
	
	
	
	</script>
</head>
	
<body>
	
<div class="mimmo">
CIAO


</div>
	
</body>
</html>