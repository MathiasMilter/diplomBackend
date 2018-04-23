$(document).ready(function(){
	ajaxGet("orders/"+ getOrderID(), loadOrderInfo);	
	ajaxGet("costumers/" + getCostumerID(), loadCostumerInfo);	
});


// Write the remaining javascripts to the page
document.write('<script src="javascript/Header-Footer.js"></script>');
document.write('<script src="javascript/customAjax.js"></script>');
document.write('<script src="javascript/controllers/localStorageController.js"></script>');
document.write('<script src="javascript/util.js"></script>');



function loadOrderInfo(obj){
		
		let data = obj;

		document.getElementById("orderID").innerHTML = data.orderID;
		document.getElementById("prizeString").innerHTML = data.prizeString;
		document.getElementById("time").innerHTML = data.time;
		document.getElementById("description").innerHTML = data.description;

}

function loadCostumerInfo(obj){
	
	let data = obj;

	document.getElementById("name").innerHTML = data.name;
	document.getElementById("adress").innerHTML = data.adress;
	document.getElementById("phoneNumber").innerHTML = data.phoneNumber;
	document.getElementById("email").innerHTML = data.email;

}


function demoFromHTML() {
    var pdf = new jsPDF('l', 'pt', 'letter');
    var options = {
        background: '#fff' //background is transparent if you don't set it, which turns it black for some reason.
    };
    pdf.addHTML($('#content')[0], options, function () {
            pdf.save('Test.pdf');
    });
}
