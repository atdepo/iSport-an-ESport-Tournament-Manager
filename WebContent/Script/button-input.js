$(document).ready(function(){


$('.next').click(function (e) { 
	if($(event.target).hasClass('budget')){
		
	    var max_value=1000000;
	    var curr_val=parseInt($('.number-box-budget').text());
	    if(e.shiftKey){
	    	if(curr_val+100<=max_value)
	            $('.number-box-budget').html(curr_val+100+' €'); 
	    }
	    else if(e.ctrlKey){
	    	if(curr_val+1000<=max_value)
	            $('.number-box-budget').html(curr_val+1000+' €'); 
	    }
	    else if(curr_val+1<=max_value)
	        $('.number-box-budget').html(curr_val+1+' €');
	} 
	else if($(event.target).hasClass('tecnici')){
		
		
		 var max_value=$('.tot-tecnici').val();
		    var curr_val=parseInt($('.number-box-tecnici').text());
		    if(e.shiftKey){
		    	if(curr_val+100<=max_value)
		            $('.number-box-tecnici').html(curr_val+100); 
		    }
		    else if(e.ctrlKey){
		    	if(curr_val+1000<=max_value)
		            $('.number-box-tecnici').html(curr_val+1000); 
		    }
		    else if(curr_val+1<=max_value)
		        $('.number-box-tecnici').html(curr_val+1);
			}
    
     });






$('.prev').click(function (e) { 
	
	
if($(event.target).hasClass('budget')){
	    var min_value=0;
	    var curr_val=parseInt($('.number-box-budget').text());
	    if(e.shiftKey){
	    	if(curr_val-100>=min_value)
	            $('.number-box-budget').html(curr_val-100+' €'); 
	    }
	    else if(e.ctrlKey){
	    	if(curr_val-1000>=min_value)
	            $('.number-box-budget').html(curr_val-1000+' €'); 
	    }
	    else if(curr_val-1>=min_value)
	        $('.number-box-budget').html(curr_val-1+' €');
	} 
	else if($(event.target).hasClass('tecnici')){
		
		 var min_value=0;
		    var curr_val=parseInt($('.number-box-tecnici').text());
		    if(e.shiftKey){
		    	if(curr_val-100>=min_value)
		            $('.number-box-tecnici').html(curr_val-100); 
		    }
		    else if(e.ctrlKey){
		    	if(curr_val-1000>=min_value)
		            $('.number-box-tecnici').html(curr_val-1000); 
		    }
		    else if(curr_val-1>=min_value)
		        $('.number-box-tecnici').html(curr_val-1);
			}
	
});

})