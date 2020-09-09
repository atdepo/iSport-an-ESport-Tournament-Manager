/**
 * 
 */

 function toggle(){
	
	if($('#user-img').val()!=="null"){
		
		if($('#check').prop('checked')){
			$('ul li').each(function(i){
				if(i===3){
					var ctx=window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
					$(this).empty().html('<li><a href="'+ctx+'/Logout">LOG OUT</a></li>');
				}
			})
		} else{
			$('ul li').each(function(i){
				if(i===3){
					$(this).empty().html('<div class="div-profile-image"><a href=""><img src="'+$('#user-img').val()+'" class="source"></a></div>');
				}
				$('.prof').empty();
			})
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
		$('#link').append('<li class="prof"><a>TEST</a></li>');
	}
	else{
		$('#link').empty();
	}
});
