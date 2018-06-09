$(document).ready(function(){
	loadShoppingLists();
});

function loadShoppingLists(){
	$.ajax({
		url: 'rest/shoppingList/ownedBy/' + getUsername(),
		datatype:'application/json',
		type: 'GET',
		contentType: 'application/json',
		beforeSend: function (xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getToken());
		},
		success: function(data) {
			
			for (var i = 0; i<data.length; i++){
				$('ul').append( '<li class = "mb-3"><div class = "row">' +
						'<div class = "col">'+ data[i].name +'</div>'+
						'<div class = "col">'+'<div class = "text-right"><a id = '+data[i].shoppingListID+' onClick = "chooseShoppingList(this.id)" class="btn btn-info mr-2">Choose</a></div></div>'+
						'</div></li>');
			}
			

		}
		
		
	});
}

function chooseShoppingList(shoppingListID){
	setShoppingListID(shoppingListID);
	
	
	window.location = "productTable.html";
	
}

function submitForm(){
	if (checkForData()){
		var data =
		{
				"name" : $("#nameInput").val(),
				"count" : $("#countInput").val()
		}
		$.ajax({
			url:'rest/products',
			datatype:'application/json',
			type: 'POST',
			contentType: 'application/json',
			data : JSON.stringify(data),
			async:false,
			processData: false,
			beforeSend: function (xhr) {
				/* Authorization header */
				xhr.setRequestHeader("Authorization", "Bearer " + getToken());
			},	success: function(data) {
				location.reload();
			},
			error: function (error) {
				alert('You need to login.');
				window.location = "index.html";
			}
		});
		
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