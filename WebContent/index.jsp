<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="CSS/index.css" type="text/css">
	<meta charset="UTF-8">
	<title>"Negozio di modelle da torneo"</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	<div class="intro-section" style="background-image: url('img/homeBackground.jpg');">
		<h1>Il Posto Perfetto per Competere</h1>
    
	</div>
			
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
      <img src="la.jpg" alt="Los Angeles">
    </div>

    <div class="item">
      <img src="../img/arena1.jpg" alt="Chicago">
    </div>

    <div class="item">
      <img src="ny.jpg" alt="New York">
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
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