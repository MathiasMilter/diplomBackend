
function createUser(){
	if (checkForData()){
			var data =
			{
					"name" : $("#createName").val(),
					"email" : $("#createEmail").val(),
					"username" : $("#createUsername").val(),
					"password" : $("#createPassword").val()
			}
			$.ajax({
				url:'rest/login/createUser',
				datatype:'application/json',
				type: 'POST',
				contentType: 'application/json',
				data : JSON.stringify(data),
				async:false,
				processData: false,
				success: function(data) {
					setToken(data);
					window.location = 'index.html';
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
	email = document.getElementById('createEmail').value;
	username = document.getElementById('createUsername').value;
	password = document.getElementById('createPassword').value;

	if (name == "") {
		errormessage += "Indtast username:  \n";
		document.getElementById('createName').style.borderColor = "red";
	}

	if (email == "") {
		errormessage += "Indtast password: \n";
		document.getElementById('createEmail').style.borderColor = "red";
	}
	if (username == "") {
		errormessage += "Indtast username:  \n";
		document.getElementById('createUsername').style.borderColor = "red";
	}

	if (password == "") {
		errormessage += "Indtast password: \n";
		document.getElementById('createPassword').style.borderColor = "red";
	}

	if (validateEmail(email)){
		document.getElementById('createEmail').style.borderColor = "Green";
	} else {
		errormessage += "Not a valid email\n";
		document.getElementById('createEmail').style.borderColor = "red";
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