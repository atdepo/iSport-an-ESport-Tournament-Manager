var session;
$(document).ready(function(){
	session=$('#session').val();
	
	
})

function toggle(tipo){
	
	if($('#user-img').val()!=="null"){
		var ctx=window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
		if($('#check').prop('checked')){
			$('ul li').each(function(i){
				if(i===3){
					if(tipo=="utente"){
						$(this).empty().html('<li><a href="'+ctx+'/user/Profilo.jsp">IL MIO PROFILO</a></li>');
					}
					else if(tipo=="admin"){
						$(this).empty().html('<li><a href="'+ctx+'/admin/Admin.jsp">IL MIO PANNELLO</a></li>');
					}
					else if(tipo=="tecnico"){
						$(this).empty().html('<li><a href="'+ctx+'/tecnico/messaggi.jsp">I MIEI MESSAGGI</a></li>');
					}
				}
				
				
			})
			
			
			$('#link').append('<li class="out"><a href="'+ctx+'/Logout;jsessionid='+session+'">LOG-OUT</a></li>');

		} else{
			$('ul li').each(function(i){
				if(i===3){
					$(this).empty().html('<div class="div-profile-image"><a href=""><img src="'+$('#user-img').val()+'" class="source"></a></div>');
				}
			})			
			$('.out').empty();

		}
	}
}
$(function() {
	var dropDownMenu = $(".avatar-dropdown-menu");

	dropDownMenu.click(function(e) {		
		e.stopPropagation();

		$(document).on("click", menuCloseListener);

		toggleMenu();
	});

	var toggleMenu = function() {
		dropDownMenu.toggleClass("open");
	}

	var menuCloseListener = function() {
		toggleMenu();

		$(document).off("click", menuCloseListener);
	}
});

window.addEventListener("resize", function(){	
	if(document.body.clientWidth<1180){
	//	alert('frate troppo piccola');
		//$('#link').append('<li class="prof"><a>TEST</a></li>');
	}
	else{
		$('.prof').empty();
	}
});
