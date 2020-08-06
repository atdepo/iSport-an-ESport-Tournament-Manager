
$(document).ready(function() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {

			let data = JSON.parse(xhr.responseText);
			console.log(data);
			var game = $('#gioco');
			var strutture = $('#strutture');
			var dataStrutture= data['0'];
			var dataGiochi= data['1'];
			var numTecnici= data['2'];
			document.getElementById("tot_tecnici").setAttribute("max" , numTecnici);

			/* for (var i = 0; i < dataStrutture.length; i++) {
				strutture.append('<option>' + dataStrutture[i].nome+', '+dataStrutture[i].indirizzo+' - '+dataStrutture[i].CAP+ '</option>');	

			} */
			
			/*const selected = document.querySelector(".selected");
			const optionsContainer = $(".options-container");

			selected.addEventListener("click", () => {
				alert('mammt');
			  optionsContainer.addClass("active");
			});
*/
			
				for (var i = 0; i < dataStrutture.length; i++) {
					strutture.append('<div class="option"><input name="struttura" onclick="tendina()" type="radio" class="radio" id="'+dataStrutture[i].nome+'"> <label for="'+dataStrutture[i].nome+'">'+dataStrutture[i].nome+', '+dataStrutture[i].indirizzo+' - '+dataStrutture[i].CAP+' </label></div>');	
			
			}
				


				
				//const optionsList = document.querySelectorAll(".option");
				
				/*optionsList.forEach(o => {
				  o.addEventListener("click", () => {
						alert('mammt');

				    selected.innerHTML = o.querySelector("label").innerHTML;
				    optionsContainer.classList.remove("active");
				  });
				});*/	
				
				
				
				
				
			for (var i = 0; i < dataGiochi.length; i++) {
				game.append('<option>' + dataGiochi[i].nomeGioco + '</option>');	

			}			
			getMode();
		}
		
	}

	xhr.open('GET', 'TournamentControl?action=initTorneo', true);
	xhr.send();

});

function menu(){
	
$(".options-container").toggleClass("active");
	
}
function tendina(){
	var selected=$('.selected');
	var optionsContainer = $(".options-container");
	//alert($("input[name='struttura']:checked").attr("id"));
    //selected.replaceWith('<div class="selected" onclick="menu()">'+'Ciccio'+'</div>');
	//selected.text($("input[name='struttura']:checked").attr("id"));
	selected.text($("input[name='struttura']:checked").attr("id"));

	//alert($(this).html());
    optionsContainer.toggleClass("active");;
}


function getMode() {
	var gioco = $('select#gioco option:checked').val();
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			var select = $('#mode');
			select.empty();

			let data = JSON.parse(xhr.responseText);

			for (var i = 0; i < data.length; i++) {
				select.append('<option>' + data[i].tipo + '</option>');
			}
		}
	}

	xhr.open('GET', 'TournamentControl?action=getMode&gioco=' + gioco, true);
	xhr.send();

}


function numTecnici(){

	document.getElementById('tecnici_fisici').setAttribute("max",document.getElementById('tot_tecnici').value);
	
}


function validateTournament(){
	
	
	
}

