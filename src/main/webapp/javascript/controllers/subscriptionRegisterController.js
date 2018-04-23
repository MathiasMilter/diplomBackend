$(document).ready(function(){
	$(function() {
		$("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
	});
	
	if (getChangeOrder() != null){
		ajaxGet("subscriptions/costumer/" + getCostumerID(), loadSubscriptionInfo);
	}
});
//Write the remaining javascripts to the page
document.write('<script src="javascript/Header-Footer.js"></script>');
document.write('<script src="javascript/customAjax.js"></script>');
document.write('<script src="javascript/controllers/localStorageController.js"></script>');


function changeColor(x) {
	x.style.borderColor = "";
}

function checkForData()
{
	var errormessage = "", time, prize, ordre, kavi;

	time = document.getElementById('datepicker').value;
	price = document.getElementById('price').value;
	description = document.getElementById('description').value;


	if (time == "") {
		errormessage += "Venligst indsæt et tidspunkt:  \n";
		document.getElementById('datepicker').style.borderColor = "red";
	} 

	if (price == "") {
		errormessage += "Venligst indsæt en pris: \n";
		document.getElementById('price').style.borderColor = "red";
	}
	if (description == "") {
		errormessage += "Venligst indsæt en beskrivelse: \n";
		document.getElementById('description').style.borderColor = "red";
	}

	if (errormessage != "") {
		alert(errormessage);
		return false;
	} else {
		return true;
	}
};

function submitForm(){
	if (checkForData()){


			var data =
			{
				    "costumerID": getCostumerID(),
				    "description": $("#description").val(),
				    "startDate": $("#datepicker").datepicker().val(),
				    "intervalMonths": $("#intervalMonths").val(),
				    "price": $("#price").val()
			}

			$.ajax({
				url:'rest/subscriptions',
				datatype:'application/json',
				type: 'POST',
				contentType: 'application/json',
				data : JSON.stringify(data),
				processData: false
			});
		
		window.location = "costumerInfo.html";
	}
};

function loadSubscriptionInfo(obj){

	let data = obj;

	$("#datepicker").val(data.startDate);
	$("#intervalMonths").val(data.intervalMonths);
	$("#price").val(data.price);
	$("#description").val(data.description);

	$("#header").text("Redigér Abonnoment");

};