function deleteItem(){

	let URL = 'rest/products/';
	let URL_REAL = URL.concat($("#productTable").DataTable().rows( {selected : true} ).data()[0].id);
	$.ajax({
		type: "DELETE",
		url: URL_REAL
	})
	location.reload();
	
	
}


function submitForm(){
	if (checkForData()){
			var data =
			{
					"name" : $("#nameInput").val(),
					"count" : $("#countInput").val()
			}
			event.preventDefault();
			$.ajax({
				url:'rest/products',
				datatype:'application/json',
				type: 'POST',
				contentType: 'application/json',
				data : JSON.stringify(data),
				processData: false
			});
			location.reload();
	}
};

function changeColor(x) {
	x.style.borderColor = "";
}

function checkForData()
{
	var errormessage = "";

	name = document.getElementById('nameInput').value;
	count = document.getElementById('countInput').value;


	if (name == "") {
		errormessage += "Indtast navn:  \n";
		document.getElementById('nameInput').style.borderColor = "red";
	}

	if (count == "") {
		errormessage += "Indtast antal: \n";
		document.getElementById('countInput').style.borderColor = "red";
	}

	if (errormessage != "") {
		alert(errormessage);
		return false;
	} else {
		return true;
	}
};