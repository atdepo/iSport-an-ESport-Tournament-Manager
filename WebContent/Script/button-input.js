$(document).ready(function(){


$('.next').click(function (e) { 

    var max_value=1000000;
    var curr_val=parseInt($('#number-box').text());
    if(e.shiftKey){
    	if(curr_val+100<=max_value)
            $('#number-box').html(curr_val+100+' €'); 
    }
    else if(e.ctrlKey){
    	if(curr_val+1000<=max_value)
            $('#number-box').html(curr_val+1000+' €'); 
    }
    else if(curr_val+1<=max_value)
        $('#number-box').html(curr_val+1+' €');

    
        if(curr_val>=1000){
            $('.number-container').css("width", "100px");
            $('.next').hover(function () {   


            } 
            );
        }
    
});

$('.prev').click(function (e) { 
    var min_value=0;
    var curr_val=parseInt($('#number-box').text());
    if(e.shiftKey){
    	if(curr_val-100>=min_value)
            $('#number-box').html(curr_val-100+' €'); 
    }
    else if(e.ctrlKey){
    	if(curr_val-1000>=min_value)
            $('#number-box').html(curr_val-1000+' €'); 
    }
    else if(curr_val-1>=min_value)
        $('#number-box').html(curr_val-1+' €');
});

})