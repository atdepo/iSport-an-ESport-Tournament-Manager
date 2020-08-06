
var i=1;

$(document).ready(function() {

	var input=$("input[type='radio']");
		input.on( "change", function() {
		   if($("input:checked").attr("id")==="esistente"){
			   cambiaTipo();
		}
		   else{
			   alert('bambino');
		   }
		   
		});
		
		//$("#esistente").attr("checked", true);
})

function add(){


	var cont=$('.container');
	var int=0;
	cont.append('<div class="squadra2" align="right"> <h2>Squadra '+ int+' </h2><br><br><input type="text" name="nomeSquadra1" placeholder="Merda"></div>');
	
	cont.append('<div id="squadra'+i+'" class="squadra" align="right"><input type="text"'+
			'placeholder="Inserisci nome Squadra">'+
			'<input type="button" value="Delete Squadra" onclick="elimina('+i+')"><br><br></div>');
	i++;
	
}

function elimina(i){
	var toErase= "squadra"+i;
	
	var tmp=$('#'+toErase);
	tmp.empty();
	
	
}

function cambiaTipo(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {

			let data = JSON.parse(xhr.responseText);
			console.log(data);
			var cont=$('.container');
			cont
			
			
		}
		
	}
	
	xhr.open('GET', 'TournamentControl?action=getSquadre', true);
	xhr.send();
}


