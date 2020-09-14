<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	
String error_msg=(String)session.getAttribute("error-msg");	
String error_type=(String)session.getAttribute("error-tipo");	
String error_alternativa=(String)session.getAttribute("error-alt");


%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../Script/pagamento.js"></script>
<link rel="stylesheet" href="../CSS/pagamento.css" type="text/css">
</head>
<body>
	<%@ include file="../header.jsp"%>
		<input type="hidden" value="<%=request.getSession().getId()%>" id="session">
	
	<div class='container'>
		<div class='info'>
			<h1>Pagamento</h1>
		</div>
		<form class='modal' action="PagamentoControl?action=conferma" method="post">
			<header class='header'>
				<h1 class="totale">Payment of <%=session.getAttribute("budget") %></h1>
				<div class='card-type'>
					<a class='card active' href='#'> <img
						src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/169963/MC.png'>
					</a><a class='card' href='#'> <img
						src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/169963/Visa.png'>
					</a><a class='card' href='#'> <img
						src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/169963/Amex.png'>
					</a> <a class='card' href='#'> <img
						src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/169963/Discover.png'>
					</a>
				</div>
			</header>
			<div class='content'>
				<div class='form'>
					<div class='form-row'>
						<div class='input-group'>
							<label for=''>Nome Intestatario</label> <input placeholder=''
								type='text'>
						</div>
					</div>
					<div class='form-row'>
						<div class='input-group'>
							<label for=''>Numero di carta</label> <input maxlength='16'
								placeholder='' type='number'>
						</div>
					</div>
					<div class='form-row'>
						<div class='input-group'>
							<label for=''>Data Scandenza</label> <input placeholder=''
								type='month'>
						</div>
						<div class='input-group'>
							<label for=''>CVV</label> <input maxlength='3' placeholder=''
								type='number'>
						</div>
					</div>
				</div>
			</div>
			<footer class='footer'>
				<input type="submit" class='button' value="Completa pagamento">
			</footer>
		</form>
	</div>


<input type="hidden" id="error-msg" value="<%=error_msg%>">
<input type="hidden" id="error-type" value="<%=error_type%>">
<input type="hidden" id="error-msg" value="<%=error_alternativa%>">



</body>
</html>