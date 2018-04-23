//Getter
function getCostumerID(){
	return localStorage.costumerID;
}

function getOrderID(){
	return localStorage.orderID;
}

function isSelected(){
	return localStorage.Selected;
}

function getSubscriptionButtonText(){
	return localStorage.subscriptionButtonText;
}

function getChangeOrder(){
	return localStorage.changingOrder;
}

function changeOrder(){
	localStorage.setItem("changingOrder", true);
}

function unchangeOrder(){
	localStorage.removeItem("changingOrder");
}

//setter
function setCostumerID(costumerID){
	localStorage.setItem("costumerID", costumerID);
}

function setOrderID(orderID){
	localStorage.setItem("orderID", orderID);
}

function select(){
	localStorage.setItem("Selected", true);
}

function deSelect(){
	localStorage.removeItem("Selected");
}

function setSubscriptionButtonText(subscriptionButtonText){
	localStorage.setItem("subscriptionButtonText", subscriptionButtonText);
}


//service
function resetLocalStorage(){
	localStorage.clear();
}


