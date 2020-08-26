<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body{
	color:#45A29E;
	background:url(img/black-gradient.png) repeat;
}

h2{
	text-align:center;
	padding-top:1%;
}

p{
	display:inline-block;
}

p.nome{
	font-size: 20px;
}

.link:hover{		
		background-color:gray;
  }
a{
	color:lime;
	padding-left:1%;
}
</style>

	<meta charset="UTF-8">

</head>
<body>
	<%@ include file="../header.jsp"%>
	
	<h2>Contattaci Personalmente</h2>
	<br>
	<div class="Adp">
		<p class="nome">Antonio Della Porta:</p>
		<a class="link" href="https://www.facebook.com/antonio.dellaporta.161">Facebook</a>
		<a class="link" href="https://twitter.com/tekhorror">Twitter</a>
		<a class="link" href="https://www.instagram.com/thatset.h/?hl=it">Instagram</a>
	</div>
	<br>
	<div class="ndtefu">
		<p class="nome">Antonio Renato Montefusco:</p>
		<a class="link" href="https://www.facebook.com/antonio.montefusco.35">Facebook</a>
		<a class="link" href="https://twitter.com/tekhorror">Twitter</a>
		<a class="link" href="https://www.instagram.com/ndtefu/?hl=">Instagram</a>
	</div>
	<br>
	<div class="Ciro">
		<p class="nome">Ciro Maiorino:</p>
		<a class="link" href="https://www.facebook.com/ciro.maiorino.56/">Facebook</a>
		<a class="link" href="https://twitter.com/tekhorror">Twitter</a>
		<a class="link" href="https://www.instagram.com/tekhorror/?hl=it">Instagram</a>
	</div>
</body>
</html>