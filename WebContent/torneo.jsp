<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<%

	String codice=(String)session.getAttribute("cod");
	System.out.println(codice);
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="Script/torneo.js"></script>
<title>Insert title here</title>
</head>
<body>

</body>

<input type="hidden" value="<%=codice%>" id="torneo">
</html>