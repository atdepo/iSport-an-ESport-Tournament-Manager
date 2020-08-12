<%@page import="java.util.Enumeration"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>

<script>
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
if(urlParams.get('nomesquadra')===""||!urlParams.has('nomesquadra')){
	alert('Per aggiungere una nuova squadra bisogna inserire il nome della squadra');
	window.location.replace('FormInserimentoSquadre.jsp');
}
</script>


<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="Script/insGiocatori.js"></script>

<meta charset="UTF-8">
<title>MIMMO</title>
<link rel="stylesheet" href="CSS/giocatori.css" type="text/css">
<link rel="stylesheet" href="CSS/Tendina.css" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Lato:100,200,300,400,500,600,700' rel='stylesheet' type='text/css'>

<a href="http://scribblerockerz.com/drag-n-drop-file-input-without-javascript/" class="back-to-article" target="_blank">back to Article</a>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<input type="hidden" id="nPartecipanti" value=<%=(Integer)session.getAttribute("numPartecipanti")%>>
	
	
	<form method="post" action="fileupload" name="echo" enctype="multipart/form-data">
		<h1><strong>File upload</strong> with style and pure CSS</h1>
  
  <div class="form-group">
    <label for="title">Title <span>Use title case to get a better result</span></label>
    <input type="text" name="title" id="title" class="form-controll"/>
  </div>
  <div class="form-group">
    <label for="caption">Caption <span>This caption should be descriptiv</span></label>
    <input type="text" name="caption" id="caption" class="form-controll"/>
  </div>
  
  <div class="form-group file-area">
        <label for="images">Images <span>Your images should be at least 400x300 wide</span></label>
    <input type="file" name="images" id="images" required="required" multiple="multiple"/>
    <div class="file-dummy">
      <div class="success">Great, your files are selected. Keep on.</div>
      <div class="default">Please select some files</div>
    </div>
  </div>
  
  <div class="form-group">
    <button type="submit">Upload images</button>
  </div>
	</form>


</body>
</html>