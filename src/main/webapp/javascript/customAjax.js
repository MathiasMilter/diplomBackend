function ajaxGet(urlAppend, functionName) {

	let URL = 'rest/';
	let URL_real = URL.concat(urlAppend);

	return $.ajax({
		type: "GET",
		dataType: 'json',
		url: URL_real,
		success: functionName
	});
};

function ajaxPut(urlAppend) {

	let URL = 'rest/';
	let URL_real = URL.concat(urlAppend);

	return $.ajax({
		type: "PUT",
		url: URL_real
	});
};

function ajaxDelete(urlAppend) {

	let URL = 'rest/';
	let URL_real = URL.concat(urlAppend);

	return $.ajax({
		type: "DELETE",
		url: URL_real
	});
};

