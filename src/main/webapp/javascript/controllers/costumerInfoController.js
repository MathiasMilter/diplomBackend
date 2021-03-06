$(document).ready(function(){
	ajaxGet("costumers/" + getCostumerID(), loadCostumerInfo);
	ajaxGet("subscriptions/costumer/" + getCostumerID(), loadSubscriptionInfo);
	
});



//Write the styling to the page
document.write('<link rel="stylesheet" href="css/buttons.dataTables.min.css">');
document.write('<link rel="stylesheet" href="css/jquery.datatables.min.css">');

//Write the datatables library to the page
document.write('<script src="js/DataTables/jquery.datatables.min.js"></script>');
document.write('<script src="js/DataTables/Buttons-1.5.1/js/dataTables.buttons.min.js"></script>');
document.write('<script src="js/DataTables/Buttons-1.5.1/js/buttons.flash.min.js"></script>');
document.write('<script src="js/DataTables/JSZip-2.5.0/jszip-3.js"></script>');
document.write('<script src="js/DataTables/pdfmake-0.1.32/pdfmake.min.js"></script>');
document.write('<script src="js/DataTables/pdfmake-0.1.32/vfs_fonts.js"></script>');
document.write('<script src="js/DataTables/Buttons-1.5.1/js/buttons.html5.min.js"></script>');
document.write('<script src="js/DataTables/Buttons-1.5.1/js/buttons.print.min.js"></script>');
document.write('<script src= "js/DataTables/dataTables.buttons.min.js"></script>');

//Write my own javascript to the page
document.write('<script src="javascript/Header-Footer.js"></script>');
document.write('<script src="javascript/customAjax.js"></script>');
document.write('<script src="javascript/controllers/localStorageController.js"></script>');
document.write('<script src="javascript/util.js"></script>');

document.write('<script src="javascript/dataTables/orderSelector.js"></script>');




function loadCostumerInfo(obj){
		
		let data = obj;

		document.getElementById("name").innerHTML = data.name;
		document.getElementById("adress").innerHTML = data.adress;
		document.getElementById("email").innerHTML = data.email;
		document.getElementById("phoneNumber").innerHTML = data.phoneNumber;

}

function loadSubscriptionInfo(obj){
	
	let data = obj;
	
	document.getElementById("startDate").innerHTML = data.startDate;
	document.getElementById("intervalMonths").innerHTML = data.intervalMonths;
	document.getElementById("nextOrder").innerHTML = data.nextOrder;
	document.getElementById("priceString").innerHTML = data.priceString;
	document.getElementById("description").innerHTML = data.description;

}

