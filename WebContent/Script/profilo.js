/**
 * 
 */
 $(document).ready(function(){
 	
 	if($('#user-pIVA').val()!=="           "){
 		$('.dati').append('<div class="field"><label for="iva" class="testlabel">Partita IVA</label><input type="text" class="iva" disabled placeholder="'+$('#user-pIVA').val()+'" name="iva"></div>')
 	}
 
 })