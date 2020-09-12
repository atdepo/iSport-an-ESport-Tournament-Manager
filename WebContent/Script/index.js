/*var xhr = new XMLHttpRequest();
xhr.open('GET', 'IndexControl?action=TournamentHome', true);	
xhr.send();
*/

alert('par o frat ro cazz');

$(document).ready(function() {

	
    setInterval(function () {
        moveRight();
    }, 10000);
 
  
	var slideCount = $('#slider ul li').length;
	var slideWidth = $('#slider ul li').width();
	var slideHeight = $('#slider ul li').height();
	var sliderUlWidth = slideCount * slideWidth;
	
	$('#slider').css({ width: slideWidth, height: slideHeight });
	
	$('#slider ul').css({ width: sliderUlWidth, marginLeft: - slideWidth });
	
    $('#slider ul li:last-child').prependTo('#slider ul');

    function moveLeft() {
        $('#slider ul').animate({
            left: + slideWidth
        }, 200, function () {
            $('#slider ul li:last-child').prependTo('#slider ul');
            $('#slider ul').css('left', '');
        });
    };

    function moveRight() {
        $('#slider ul').animate({
            left: - slideWidth
        }, 200, function () {
            $('#slider ul li:first-child').appendTo('#slider ul');
            $('#slider ul').css('left', '');
        });
    };





	/*
	var slider=$(".slider");
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);		
			
			for (var i = 0; i < data.length; i++) {
				var num=(data[i].codice % 4)+1 ;
				slider.append('<li id="'+data[i].codice+'"><a href="UserControl?action=visualizza&codtorneo='+data[i].codice+'"><img src="img/arena-'+num+'.jpg"></a>  </li>');
			}
		}
	

	}

	*/

	
});