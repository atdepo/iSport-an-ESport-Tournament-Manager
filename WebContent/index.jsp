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
        <div class="How">
        	<div class="row"></div>
        		<p class="work">Come funziona?</p>
        		
<main class="page-content">
  <div class="card">
    <div class="content">
      <h2 class="title">Registrati</h2>
      <p class="copy">Registrati al sito e inizia a competere subito</p> 
    </div>
  </div>
  <div class="card">
    <div class="content">
      <h2 class="title">Crea il Tuo torneo</h2>
      <p class="copy">Crea il tuo torneo personale, sia online che dal vivo</p>
    </div>
  </div>
  <div class="card">
    <div class="content">
      <h2 class="title">Scegli le squadre</h2>
      <p class="copy">Seleziona le squadre che parteciperanno al tuo torneo</p>
    </div>
  </div>
  <div class="card">
    <div class="content">
      <h2 class="title">COMPETI!</h2>
      <p class="copy">Ora le squadre possono comepetere come professionisti</p>
    </div>
  </div>
</main>
        
        
        </div>
	<%@ include file="Footer.jsp"%>
</body>
</html>