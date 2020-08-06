
$(document).ready(function(){
	

	const selected = document.querySelector(".selected");
	const optionsContainer = document.querySelector(".options-container");

	const optionsList = document.querySelectorAll(".option");

	selected.addEventListener("click", () => {
		alert('mammt');
	  optionsContainer.classList.toggle("active");
	});

	optionsList.forEach(o => {
	  o.addEventListener("click", () => {
			alert('mammt');

	    selected.innerHTML = o.querySelector("label").innerHTML;
	    optionsContainer.classList.remove("active");
	  });
	});
	
	
})


