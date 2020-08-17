<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="CSS/index.css" type="text/css">
	<meta charset="UTF-8">
	<title>"Negozio di modelle da torneo"</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	<div class="intro-section" style="background-image: url('img/homeBackground.jpg');">
		<h1>Il Posto Perfetto per Competere</h1>
    
        
	</div>
	
	<div class="arene">
		<p class="caption">Arene</p>
		<div class="items">
		
		
	<ul class="slides">
   	<input type="radio" name="radio-btn" id="img-1" checked />
    <li class="slide-container">
		<div class="slide">
			<img src="img/arena1.jpg" />
        </div>
		<div class="nav">
			<label for="img-2" class="prev">&#x2039;</label>
			<label for="img-2" class="next">&#x203a;</label>
		</div>
    </li>

    <input type="radio" name="radio-btn" id="img-2" />
    <li class="slide-container">
        <div class="slide">
          <img src="img/arena2.jpg" />
        </div>
		<div class="nav">
			<label for="img-1" class="prev">&#x2039;</label>
			<label for="img-1" class="next">&#x203a;</label>
		</div>
    </li>

</ul>
		</div>
	
	</div>
        
<%@ include file="Footer.jsp"%>
</body>
</html>