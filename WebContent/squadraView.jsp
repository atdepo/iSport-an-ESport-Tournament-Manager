<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%
String teamName=(String)session.getAttribute("nome");
if(teamName==null){
	RequestDispatcher disp= request.getRequestDispatcher("errorPage404.jsp");
	disp.forward(request, response);
}
%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="Script/squadraView.js"></script>

	<style type="text/css">
		.giocatori{
			display:flex;
			flex-wrap: wrap;
			margin-top:24px;
		}
		.giocatori > *{	
			margin:5px 10px 10px;
		}
		
		body{
			color:#0082e6;
			background:url(img/black-gradient.png) repeat;
		}
		
		small{
		font-size:20px;
		font-weight:800;
		color:#0089b6;
		}
		
		span{
		color:red;}
	</style>	
	
	


</head>
<body>
		<%@ include file="header.jsp"%>
			<input type="hidden" value="<%=request.getSession().getId()%>" id="session">
		
		<input type="hidden" value="<%=teamName%>" class="teamName">
	
	<div class="tutto">
		<div class="nomeSquadra"><h3><%=teamName%></h3></div>
		<div class="giocatori"></div>
		<fieldset class="dati">
		</fieldset>
	
	
	
	</div>

</body>
</html>