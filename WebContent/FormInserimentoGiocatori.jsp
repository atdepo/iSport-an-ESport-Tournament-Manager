<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="Script/insGiocatori.js"></script>
<link rel="stylesheet" href="CSS/giocatori.css" type="text/css">
<meta charset="UTF-8">
<title>MIMMO</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<input type="hidden" id="giocatori" value=<%=(int)session.getAttribute("numeroGiocatori")%>>
	<!--  <h3><%=(int)session.getAttribute("numeroGiocatori")%></h3> -->
	<div class="inseriamo"></div>
	


</body>
</html>