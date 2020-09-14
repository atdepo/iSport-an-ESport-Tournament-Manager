<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<%
	String error = " ";
	String type = " ";
	String errorLocation = " ";
	if (session.getAttribute("error") != null && session.getAttribute("error-type") != null
			&& session.getAttribute("error-location") != null) {
		error = (String) session.getAttribute("error");
		type = (String) session.getAttribute("error-type");
		errorLocation = (String) session.getAttribute("error-location");
	}

	System.out.println("location " + errorLocation);
	System.out.println("error " + error);
	System.out.println("type " + type);
%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="../CSS/2RadioButton.css" type="text/css">
<script type="text/javascript" src="../Script/admin.js"></script>


<style>
li {
	margin-top: 2%;
	color: #45A29E;
}

span {
	color: white;
	background-color: red;
}

body {
	color: #45A29E;
	background: url(../img/black-gradient.png) repeat;
}

.listaTornei {
	/*transform: translate(-50%, -50%);
	float:left;
	*/
	position: relative;
	left: 25%;
	width: 50%;
	text-align: center;
	background-color: rgba(47, 50, 50, 0.5);
	padding: 35px 35px 50px;
	margin-top: 30px;
}

.lista {
	font-size: 24px;
	font-weight: bold;
}

.fa-dumpster-fire {
	color: red
}

i:hover {
	background-color: transparent;
}

p {
	padding: 10px;
	font-weight: bolder;
}

.page {
	flex-wrap: wrap;
}

#addTecnico {
	width: 20%;
	display: grid;
	text-align: center;
	font-size: 18px;
	margin-top: 10px;
	position: relative;
	left: 40%;
}

.addTecnico {
	font-size: 20px;
	font-weight: bold;
	text-align: center;
	margin-top: 15px;
}
</style>
<meta charset="UTF-8">

</head>

<body>
	<%@ include file="../header.jsp"%>
	<input type="hidden" value="<%=request.getSession().getId()%>"
		id="session">

	<div class="page">

		<div class="listaTornei">
			<label class="lista">Lista Tornei</label>
		</div>

		<div class="addTecnico">
			<label> Aggiungi Profilo Tecnico</label>
			<form
				action="Admin;jsessionid=<%=request.getSession().getId()%>?action=addTecnico"
				method="post" id="addTecnico">
				<label for="nome">Nome</label> <input type="text" id="nome"
					name="nome"> <span class="error">
					<%
						if (type.equals("nome")) {
					%><%=error%>
					<%
						}
					%>
				</span> <label for="cognome">Cognome</label> <input type="text"
					id="cognome" name="cognome"> <span class="error">
					<%
						if (type.equals("cognome")) {
					%><%=error%>
					<%
						}
					%>
				</span> <label for="dataN">Data di nascita</label> <input type="date"
					id="dataN" name="dataN"> <span class="error">
					<%
						if (type.equals("dataN")) {
					%><%=error%>
					<%
						}
					%>
				</span> <label for="indirizzo">Indirizzo</label> <input type="text"
					id="indirizzo" name="indirizzo"> <span class="error">
					<%
						if (type.equals("indirizzo")) {
					%><%=error%>
					<%
						}
					%>
				</span> <label for="recapito">Recapito Tel.</label> <input type="text"
					id="recapito" name="recapito"> <span class="error">
					<%
						if (type.equals("recapito")) {
					%><%=error%>
					<%
						}
					%>
				</span> <label for="CF">Codice Fiscale</label> <input type="text" id="CF"
					name="CF"> <span class="error">
					<%
						if (type.equals("CF")) {
					%><%=error%>
					<%
						}
					%>
				</span> <label for="email">E-mail</label> <input type="email" id="email"
					name="email"> <span class="error">
					<%
						if (type.equals("email")) {
					%><%=error%>
					<%
						}
					%>
				</span> <label for="password">Password</label> <input type="password"
					id="password" name="password"> <span class="error">
					<%
						if (type.equals("password")) {
					%><%=error%>
					<%
						}
					%>
				</span>
				<div class="radioChoose">
					<input id="toggle-on" class="toggle toggle-left" name="toggle"
						value="on-line" type="radio" checked> <label
						for="toggle-on" class="btn">On-line</label> <input id="toggle-off"
						class="toggle toggle-right" name="toggle" value="locale"
						type="radio"> <label for="toggle-off" class="btn">Locale</label>
				</div>
				<input type="submit">
			</form>
		</div>
	</div>
</body>
</html>