/**
 * 
 */
 $(document).ready(function(){
 	
 	if($('#user-pIVA').val()!=="           "){
 		$('.dati').append('<div class="gertrude"><div class="field"><label for="iva" class="testlabel">Partita IVA</label><input type="text" class="iva" disabled placeholder="'+$('#user-pIVA').val()+'" name="pIVA"><i class="fas fa-user-edit iva"></i></div></div>')
 	}
 
 	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == 4) {
			let data = JSON.parse(xhr.responseText);
			console.log(data);			
			var div=$("#mieiTornei");
			if(data.length==0)
				div.append("<h3>Non hai tornei, dai ordina il tuo primo torneo dalla sezione Crea Tornei</h3>");
			for (var i = 0; i < data.length; i++) {
				div.append('<h3>Nome='+data[i].nome+'</h3><h3>  Data='+data[i].data+'</h3><h3> Luogo='+data[i].indirizzoStruttura+' '+data[i].CAPStruttura+'</h3><h3> Gioco='+data[i].codGioco+'</h3><br>');	
	
			}
		
		}
	

}
xhr.open('GET', '../UserControl?action=getMieiTornei', true);		
xhr.send();
 })
 
 
$(function() {
	var i=$('i');
	i.attr("title","modifica");
	i.click(function() {
		if(event.target.id){
			var vecchia=prompt("Inserisci la tua vecchia password");
			
			var xhr = new XMLHttpRequest();
			xhr.open('GET', '../UserControl?action=vecchiaPassword?vecchia='+vecchia);
		
			xhr.send();
			 
		}
		else{
		$(this).prev().attr("disabled",false);
		$(this).prev().focus();}
	});
	
	$("input[type=text]").focusout(function() {
		if(confirm("Vuoi salvare le modifiche?")){
			var xhr = new XMLHttpRequest();
			xhr.open('GET', '../UserControl?action=change&cosa='+$(this).attr("name")+'&valore='+$(this).val(), true);		
			xhr.send();
		}
	})
})