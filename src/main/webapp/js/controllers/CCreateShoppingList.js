
function createShoppingList(){
	if (checkForData()){
			var data =
			{
					"name" : $("#createName").val(),
					"ownerUsername" : getUsername()
			}
			$.ajax({
				url:'rest/shoppingList',
				datatype:'application/json',
				type: 'POST',
				contentType: 'application/json',
				data : JSON.stringify(data),
				async:false,
				processData: false,
				beforeSend: function (xhr) {
					/* Authorization header */
					xhr.setRequestHeader("Authorization", "Bearer " + getToken());
				},
				success: function(data) {
					window.location = 'shoppingList.html';
			    },
			    error: function (error) {
			       alert('Wrong login!');
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

	name = document.getElementById('createName').value;

	if (name == "") {
		errormessage += "Indtast navn på listen:  \n";
		document.getElementById('createName').style.borderColor = "red";
	}
	
	if (errormessage != "") {
		alert(errormessage);
		return false;
	} else {
		return true;
	}
};

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}