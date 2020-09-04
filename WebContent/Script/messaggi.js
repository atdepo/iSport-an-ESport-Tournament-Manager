$(document).ready(function() {
	getMessaggi();
	

});



function getMessaggi() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {

			let data = JSON.parse(xhr.responseText);
			console.log(data);
			
			container=$(".containerMessaggi");
			
			for (var i = 0; i < data.length; i++) {
				container.append("<div class='mess' onclick=showMessage("+data[i].codice+") id=messaggio"+data[i].codice+">Messaggio"+data[i].codice+"<a href='MessaggiControl?action=delMessaggio&codice="+data[i].codice+"'><i   class='messChecked check fas fa-check'></i></a></div>") 
						
			}
			
			
			}
		}	
	xhr.open('GET', 'MessaggiControl?action=getMessaggi', true);
		xhr.send();
		
	}

function delMessaggi(cod){
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'MessaggiControl?action=delMessaggi&codice='+cod, true);
	xhr.send();
	}
		
function showMessage(i)  {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if (xhr.status == 200 && xhr.readyState == 4) {
			
			let data = JSON.parse(xhr.responseText);
			console.log(data);
			show=$(".viewerMessaggi");
			show.html("<h4>"+data.testo+"</h4>");
			
			
			
			}
		
		}
	xhr.open('GET', 'MessaggiControl?action=showMessaggio&codice='+i, true);
	xhr.send();
	
	
	
}