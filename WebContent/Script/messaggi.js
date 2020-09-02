$(document).ready(function() {
	getMessaggi();
	
	var check=$(".check");
	check.click(function() {
		delMessaggi(check.attr("id"));
	})
});



function getMessaggi() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {

			let data = JSON.parse(xhr.responseText);
			console.log(data);
			
			container=$(".containerMessaggi");
			
			for (var i = 0; i < data.length; i++) {
				container.append("<div onclick=showMessage("+data[i].codice+") id=messaggio"+data[i].codice+">Messaggio"+data[i].codice+"</div><i id="+data[i].codice+" class='check fas fa-check'></i>")
			}
			
			
			}
		}	
	xhr.open('GET', 'MessaggiControl?action=getMessaggi', true);
		xhr.send();
		
	}

function delMessaggi(cod){
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'MessaggiControl?action=delMessaggi&codice=cod', true);
	xhr.send();
	}
		
function showMessage(i)  {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {
			
			show=$(".viewerMessaggi");
			show.text("<h4>"+data[0].testo+"</h4>");
			
			
			
			}
		
		}
	xhr.open('GET', 'MessaggiControl?action=showMessaggio&codice='+i, true);
	xhr.send();
	
	
	
}