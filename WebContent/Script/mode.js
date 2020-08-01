
$(document).ready(function(){
	
	var select=$('#mode');
	select.empty().append('<option value="">---Inizializzata al caricamento---</option>');
	var gioco=$('#gioco');
	//alert(gioco.children("option:selected").val());
});

function getMode(){

	var select = $('#mode');
	select.empty().append('<option value="">---Select mode---</option>');
	alert('sono stata triggerata');
	
	
}