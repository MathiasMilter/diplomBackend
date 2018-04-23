$(document).ready(function(){

	if (getChangeOrder() != null){
		ajaxGet("costumers/" + getCostumerID(), loadCostumerInfo);
	}

});
// Write the remaining javascripts to the page
document.write('<script src="javascript/Header-Footer.js"></script>');
document.write('<script src="javascript/customAjax.js"></script>');
document.write('<script src="javascript/controllers/localStorageController.js"></script>');


function changeColor(x) {
	x.style.borderColor = "";
}

function checkForData()
{
	var errormessage = "", wo, forv, ordre, kavi;

	name = document.getElementById('name').value;
	streetName = document.getElementById('streetName').value;
	houseNumber = document.getElementById('houseNumber').value;
	zipCode = document.getElementById('zipCode').value;
	city = document.getElementById('city').value;
	email = document.getElementById('email').value;
	phoneNumber = document.getElementById('phoneNumber').value;

	if (name == "") {
		errormessage += "Venligst indsæt et navn:  \n";
		document.getElementById('name').style.borderColor = "red";
	}

	if (streetName == "") {
		errormessage += "Venligst indsæt et gadenavn: \n";
		document.getElementById('streetName').style.borderColor = "red";
	}

	if (houseNumber == "") {
		errormessage += "Venligst indsæt et husnummer: \n";
		document.getElementById('houseNumber').style.borderColor = "red";
	}

	if (zipCode == "") {
		errormessage += "Venligst indsæt et postnummer: \n";
		document.getElementById('zipCode').style.borderColor = "red";
	}

	if (city == "") {
		errormessage += "Venligst indsæt en by: \n";
		document.getElementById('city').style.borderColor = "red";
	}
	if (email == "") {
		errormessage += "Venligst indsæt en email: \n";
		document.getElementById('email').style.borderColor = "red";
	}
	if (phoneNumber == "") {
		errormessage += "Venligst indsæt et telefon nummer: \n";
		document.getElementById('phoneNumber').style.borderColor = "red";
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
		putCostumer();
	} else {
		submitForm();
	}
}


function submitForm(){
	if (checkForData()){
			var data =
			{
					"name" : $("#name").val(),
					"email" : $("#email").val(),
					"phoneNumber" : $("#phoneNumber").val(),
					"streetName" : $("#streetName").val(),
					"houseNumber" : $("#houseNumber").val(),
					"zipCode" : $("#zipCode").val(),
					"city" : $("#city").val()
			}
			event.preventDefault();
			$.ajax({
				url:'rest/costumers',
				datatype:'application/json',
				type: 'POST',
				contentType: 'application/json',
				data : JSON.stringify(data),
				processData: false
			});
			window.location = "costumerSelector.html";
	}
};

function putCostumer(){
	if (checkForData()){
			var data =
			{
					"name" : $("#name").val(),
					"email" : $("#email").val(),
					"phoneNumber" : $("#phoneNumber").val(),
					"streetName" : $("#streetName").val(),
					"houseNumber" : $("#houseNumber").val(),
					"zipCode" : $("#zipCode").val(),
					"city" : $("#city").val()
			}
			event.preventDefault();
			$.ajax({
				url:'rest/costumers/' + getCostumerID(),
				datatype:'application/json',
				type: 'PUT',
				contentType: 'application/json',
				data : JSON.stringify(data),
				processData: false
			});
			window.location = "costumerSelector.html";
	}
};

function loadCostumerInfo(obj){

	let data = obj;

	$("#name").val(data.name);
	$("#email").val(data.email);
	$("#phoneNumber").val(data.phoneNumber);
	$("#streetName").val(data.adressObject.streetName);
	$("#houseNumber").val(data.adressObject.houseNumber);
	$("#zipCode").val(data.adressObject.zipCode);
	$("#city").val(data.adressObject.city);

	$("#header").text("Redigér Kunde");






};
