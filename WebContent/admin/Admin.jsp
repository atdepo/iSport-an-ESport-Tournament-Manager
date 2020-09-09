<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

   
<%
String error=" ";
String type=" ";
String errorLocation=" ";
if(session.getAttribute("error")!=null && session.getAttribute("error-type")!=null && session.getAttribute("error-location")!=null){
	error = (String)session.getAttribute("error");
	type= (String)session.getAttribute("error-type");	
	errorLocation =(String)session.getAttribute("error-location");
}

System.out.println("location "+errorLocation);
System.out.println("error "+error);
System.out.println("type "+type);


%>
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript" src="../Script/admin.js"></script>


	<style>
	li{
		margin-top: 2%;
		color:#45A29E;
	}
	
	body{
		color:#45A29E;
		background:url(../img/black-gradient.png) repeat;
	}
	
	.listaTornei{	
    /*transform: translate(-50%, -50%);
	float:left;
	*/
	position: absolute;
    left: 50%;
	width:550px;
	margin:auto;
	text-align: center;	
	background-color:rgba(47,50,50,0.5);
	padding: 35px 35px 50px;
  	margin-top:30px;
	margin-left: -260px;
	
  	-moz-border-radius: 7px;
  	-webkit-border-radius: 7px;
  	border-radius:7px;}
  	
	.fa-dumpster-fire{
		color: red
	}
	i:hover{background-color: transparent;}
	p{
		padding: 10px;
		font-weight: bolder;
	}
	</style>
	<meta charset="UTF-8">
	
</head>
	
<body>
	<%@ include file="../header.jsp"%>
	<div class="listaTornei"></div>
	
	
	<form action="Admin?action=addTecnico" method="post" id="addTecnico">
		<input type="text" id="nome" name="nome">
		 <span class="error"><%if(type.equals("nome")){ %><%=error%><%}%></span>
		<input type="text" id="cognome" name="cognome">
		<span class="error"><%if(type.equals("cognome")){ %><%=error%><%}%></span>
		<input type="date" id="dataN" name="dataN">
		<span class="error"><%if(type.equals("dataN")){ %><%=error%><%}%></span>
		<input type="text" id="indirizzo" name="indirizzo">
		<span class="error"><%if(type.equals("indirizzo")){ %><%=error%><%}%></span>
		<input type="text" id="recapito" name="recapito">
		<span class="error"><%if(type.equals("recapito")){ %><%=error%><%}%></span>
		<input type="text" id="CF" name="CF">
		<span class="error"><%if(type.equals("CF")){ %><%=error%><%}%></span>
		<input type="email" id="email" name="email">
		<span class="error"><%if(type.equals("email")){ %><%=error%><%}%></span>
		<input type="password" id="password" name="password">
		<span class="error"><%if(type.equals("password")){ %><%=error%><%}%></span>
		
		<input id="toggle-on" class="toggle toggle-left" name="toggle" value="online" type="radio" checked>
		<label for="toggle-on" class="btn">Online</label>
		<input id="toggle-off" class="toggle toggle-right" name="toggle" value="fisico" type="radio">
		<label for="toggle-off" class="btn">Fisico</label>
		
		<input type="submit">
	</form>
	
</body>
</html>