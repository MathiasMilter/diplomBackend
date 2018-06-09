//-------------------------------------------------
//
// 					TOKEN
//
//-------------------------------------------------

function setToken(token){
	sessionStorage.setItem("token", token);
}

function getToken(){
	return sessionStorage.getItem("token");
}

function removeToken(){
	sessionStorage.removeItem("token");
}

//-------------------------------------------------
//
// 					USERNAME
//
//-------------------------------------------------

function setUsername(username){
	sessionStorage.setItem("username", username);
}

function getUsername(){
	return sessionStorage.getItem("username");
}

function removeUsername(){
	sessionStorage.removeItem("username");
}


//-------------------------------------------------
//
// 					SHOPPINGLISTID
//
//-------------------------------------------------

function setShoppingListID(shoppingListID){
	sessionStorage.setItem("shoppingListID", shoppingListID);
}

function getShoppingListID(){
	return sessionStorage.getItem("shoppingListID");
}

function removeShoppingListID(){
	sessionStorage.removeItem("shoppingListID");
}
