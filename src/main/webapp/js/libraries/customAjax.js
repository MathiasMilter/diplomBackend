function ajaxPost(urlAppend) {

	let URL = 'rest/';
	let URL_real = URL.concat(urlAppend);

	return $.ajax({
		type: 'POST',
		url: URL_real,
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		async: false
	});
};

function ajaxPut(urlAppend, data, functionName) {

	let URL = 'rest/';
	let URL_real = URL.concat(urlAppend);

	return $.ajax({
		type: 'PUT',
		url: URL_real,
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		success: functionName
	});
};

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
