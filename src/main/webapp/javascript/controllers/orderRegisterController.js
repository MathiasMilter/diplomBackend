$(document).ready(function(){
	$(function() {
		$("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
	});
	
	if (getChangeOrder() != null){
		ajaxGet("orders/" + getOrderID(), loadOrderInfo);
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
	prize = document.getElementById('prize').value;


	if (time == "") {
		errormessage += "Venligst indsæt et tidspunkt:  \n";
		document.getElementById('datepicker').style.borderColor = "red";
	} 

	if (prize == "") {
		errormessage += "Venligst indsæt en pris: \n";
		document.getElementById('prize').style.borderColor = "red";
	}

	if (errormessage != "") {
		alert(errormessage);
		return false;
	} else {
		return true;
	}
};

function sendData(){
	if (getChangeOrder() != null){
		putOrder();
	} else {
		submitForm();
	}
}


function submitForm(){
	if (checkForData()){
			if ($("#payed:checked").length > 0){
				var payed = true;
			}

			if ($("#done:checked").length > 0){
				var done = true;
			}

			
			var data =
			{
					"costumerID": getCostumerID(),
					"time": $("#datepicker").datepicker().val(),
					"description": $("#description").val(),
					"done": done,
					"payed": payed,
					"prize": $("#prize").val()
			}

			$.ajax({
				url:'rest/orders',
				datatype:'application/json',
				type: 'POST',
				contentType: 'application/json',
				data : JSON.stringify(data),
				processData: false
			});

		window.location = "costumerInfo.html";
	}
};

function putOrder(){
	if (checkForData()){
		
		var payed = false;
		var done = false;
		if ($("#payed:checked").length > 0){
			payed = true;
		}

		if ($("#done:checked").length > 0){
			done = true;
		}

		
		var data =
		{
				"costumerID": getCostumerID(),
				"time": $("#datepicker").datepicker().val(),
				"description": $("#description").val(),
				"done": done,
				"payed": payed,
				"prize": $("#prize").val()
		}
		alert(JSON.stringify(data));
		$.ajax({
			url:'rest/orders/' + getOrderID(),
			datatype:'application/json',
			type: 'PUT',
			contentType: 'application/json',
			data : JSON.stringify(data),
			processData: false
		});

	window.location = "costumerInfo.html";
}
};

function loadOrderInfo(obj){
	
	let data = obj;

	$("#datepicker").val(data.time);
	$("#prize").val(data.prize);
	$("#description").val(data.description);


	
	if (data.payed == true){
		$("#payed").prop('checked', true);

	}
	
	if (data.done == true){
		$("#done").prop('checked', true);
	}
	
	
}