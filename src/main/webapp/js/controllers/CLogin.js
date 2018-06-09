
function login(){
	if (checkForData()){
			var data =
			{
					"username" : $("#usernameInput").val(),
					"password" : $("#passwordInput").val()
			}
			$.ajax({
				url:'rest/login',
				datatype:'application/json',
				type: 'POST',
				contentType: 'application/json',
				data : JSON.stringify(data),
				async:false,
				processData: false,
				success: function(data) {
					setToken(data);
					window.location = 'productTable.html';
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

	username = document.getElementById('usernameInput').value;
	password = document.getElementById('passwordInput').value;


	if (username == "") {
		errormessage += "Indtast username:  \n";
		document.getElementById('usernameInput').style.borderColor = "red";
	}

	if (password == "") {
		errormessage += "Indtast password: \n";
		document.getElementById('passwordInput').style.borderColor = "red";
	}

	if (errormessage != "") {
		alert(errormessage);
		return false;
	} else {
		return true;
	}
};