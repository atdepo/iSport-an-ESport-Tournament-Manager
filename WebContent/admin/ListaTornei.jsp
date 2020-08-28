<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
	
	.listaTornei{	position: absolute;
    left: 50%;

    /*transform: translate(-50%, -50%);
	float:left;
	left: 50%;
	position: absolute;*/
	width:550px;
	margin:auto;
	padding: 50px 35px 10px 35px;
	text-align: center;	
	background-color:rgba(47,50,50,0.5);
	padding-left:35px;
	padding-right:35px;
	padding-top:35px;
	padding-bottom:50px;
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
	
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
	

		
	</script>
</head>
	
<body>
	<%@ include file="../header.jsp"%>
	<div class="listaTornei"></div>
	
</body>
</html>