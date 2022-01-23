// Wait for PhoneGap to load
document.addEventListener("deviceready", onDeviceReady, false);

// PhoneGap is ready
function onDeviceReady(){
    getElem("editSaveLocalButton").addEventListener("click", saveAsset);
    getElem("clearDbButton").addEventListener("click", clearDB);
    getElem("takePhotoButton").addEventListener("click", capturePhoto);
    getElem("logInButton").addEventListener("click", logIn);
    getElem("regButton").addEventListener("click", register);
    var db = window.openDatabase("MOADB", "1.0", "MOA Database", 200000);
    db.transaction(createDB, errorCreateDB, successCreateDB);
    showCategories();
    splash();
}

// Go to login from splash screen
function splash(){
	setTimeout(function(){$.mobile.changePage($("#tabstrip-logIn"))},3000);
}

// Check Log In
function logIn(){
	var uname = getElem("username").value;
	var pword = getElem("password").value;
	
	if(uname == "" || pword == ""){
		alert("Please enter your username and password");
		return;
	}
	else if(uname != "brett" || pword != "fish"){
		alert("Invalid user name or password");
	}
	else{
		$.mobile.changePage($("#tabstrip-dashboard"));
	}
}

// Register user information
function register(){
	var uname = getElem("usernameReg").value;
	
	var validReg = validateReg(uname,null,null,null);
	
	if(validReg){
		$.mobile.changePage($("#tabstrip-assetsHome"));
	}
	else{
		return;
	}
}

// Validate user registration
function validateReg(uname,email,pword,cnfPword){
	if(uname == 'brett'){
		var labTemp = getElem("usernameRegLab").innerHTML;
		getElem("usernameRegLab").innerHTML = labTemp + " User name not available";
		return false;
	}
	else{
		return true;
	}
}

// Clear local storage
function clearDB(){
	var db = window.openDatabase("MOADB", "1.0", "MOA Database", 200000);
	db.transaction(rebuildAssets, errorDB, successDropAssets);
}

// Shorthand for document.getElementById();
function getElem(element) {
    return document.getElementById(element);
}

// Get all categories from the ASSET_CATEGORY table
function showCategories(){
	var db = window.openDatabase("MOADB", "1.0", "MOA Database", 200000);
	db.transaction(queryCategories, errorDB);
}

// Display the fetched asset categories
function listCategories(tx, categories){
	var listItems = "";
    var len = categories.rows.length;
    
    for(var i = 0; i < len; i++)
    {
    	//alert('length: ' + len + ', i: ' + i + ', id: '+ categories.rows.item(i).id);
        listItems += "<li><a href=\"#tabstrip-assets\" data-transition=\"fade\" onclick=\"sessionStorage.categoryId = " + categories.rows.item(i).id + ";\" >" + categories.rows.item(i).id + " | " + categories.rows.item(i).category + "</a></li>";
    }
    
    getElem("assetCatList").innerHTML = listItems;
    $("#assetCatList").listview("refresh");
}

// Event handler to show the assets when the Assets page loads
$("#tabstrip-assets").live("pageshow", function (event){
    showAssets();
});

// Call the database function to fetch the assets
function showAssets(){
	var db = window.openDatabase("MOADB", "1.0", "MOA Database", 200000);
	db.transaction(queryAssets, errorDB)
}

// Refresh Asset List on Assets page
function listAssets(tx, assets){
    var listItems = "";
    var len = assets.rows.length;
    var catId = sessionStorage.categoryId;
    
    //alert('listing ' + assets.rows.length + ' assets for category' + catId);
    
    for(var i = 0; i < len; i++)
    {
    	if(assets.rows.item(i).category == catId){
	    	//alert('length: ' + len + ', i: ' + i + ', id: '+ assets.rows.item(i).id + ', cat: '+ assets.rows.item(i).category);
	        listItems += "<li><a href=\"#tabstrip-editAssetDetails\" data-transition=\"fade\" onclick=\"sessionStorage.assetId = " + assets.rows.item(i).id + ";\" >" + assets.rows.item(i).id + " | " + assets.rows.item(i).description + " - " + assets.rows.item(i).make + "</a></li>";
    	}
    }
    
    getElem("assetsList").innerHTML = listItems;
    $("#assetsList").listview("refresh");
}

// Clear the list of assets
function clearAssetList(){
	getElem("assetsList").innerHTML = "";
    $("#assetsList").listview("refresh");
}

// Save a new asset's or an edited existing asset's details
function saveAsset(){
	var id = sessionStorage.assetId;
	var db = window.openDatabase("MOADB", "1.0", "MOA Database", 200000);
	
	if(id == 0){
		db.transaction(addSaveAsset, errorDB, successAssetSaveDB);
	}
	else{
		db.transaction(editSaveAsset, errorDB, successAssetSaveDB);
	}
}

/*  If id > 0, display selected asset's details for editing
 * 	else, it is a new asset
 *  id is static
*/
$("#tabstrip-editAssetDetails").live("pageshow", function (event){
    var id = sessionStorage.assetId;
    
    if(id > 0){
    	var db = window.openDatabase("MOADB", "1.0", "MOA Database", 200000);
    	db.transaction(queryAssetDet, errorDB, successDB);
    }
    else{
    	getElem("idIn").innerHTML = 'New Asset';
	    getElem("descIn").value = '';
	    getElem("categoryIn").value = '';
	    getElem("makeIn").value = '';
	    getElem("modelIn").value = '';
	    getElem("priceIn").value = '';
	    getElem("purchLocIn").value = '';
	    getElem("purchMethIn").value = '';
	    getElem("purchDateIn").value = '';
	    getElem("invoiceIn").style.display = 'none';
	    getElem("invoiceIn").src = '';
	    getElem("imageIn").style.display = 'none';
	    getElem("imageIn").src = '';
    }
    
    getElem("outputSave").innerHTML = "";
    getElem("assetCount").innerHTML = "";
});

// Display the details of the selected asset
function showAssetDet(tx, assetDet){
	var idVal = assetDet.rows.item(0).id;
    var descVal = assetDet.rows.item(0).description;
    var catVal = assetDet.rows.item(0).category;
    var makeVal = assetDet.rows.item(0).make;
    var modelVal = assetDet.rows.item(0).model;
    var priceVal = assetDet.rows.item(0).price;
    var purchLocVal = assetDet.rows.item(0).purchase_loc;
    var purchMethVal = assetDet.rows.item(0).purchase_method;
    var purchDateVal = assetDet.rows.item(0).purchase_date;
    var imageVal = assetDet.rows.item(0).image_src;
    var invoiceVal = assetDet.rows.item(0).invoice_src;
    
    getElem("idIn").innerHTML = idVal;
    getElem("descIn").value = descVal;
    getElem("categoryIn").value = catVal;
    getElem("makeIn").value = makeVal;
    getElem("modelIn").value = modelVal;
    getElem("priceIn").value = priceVal;
    getElem("purchLocIn").value = purchLocVal;
    getElem("purchLocIn").value = purchMethVal;
    getElem("imageIn").style.display = 'block';
    getElem("imageIn").src = imageVal;
    getElem("invoiceIn").style.display = 'block';
    getElem("invoiceIn").src = invoiceVal;
}

// Take a Photo
function capturePhoto(){
	navigator.camera.getPicture(onSuccess, onFail, { quality: 50, 
	    destinationType: Camera.DestinationType.FILE_URI, // return image as file which is saved to the device
		//destinationType: Camera.DestinationType.DATA_URL, // return image as Base64 encoded string of the image's data
	    targetWidth: 400,
	    targetHeight: 300,
	    //saveToPhotoAlbum: true
	    });
}

// Photo capture successful event - display the image appropriately
function onSuccess(imageData) {
    var image = getElem('imageIn');
    image.style.display = 'block';
    
    // for when FILE_URI destinationType is used - see capturePhoto() function
    image.src = imageData;
    
    // for when DATA_URL destinationType is used - see capturePhoto() function
    //image.src = "data:image/jpeg;base64," + imageData;
    
    // debugging - show the returned imageData (either the URL location of the image on the device or the Base64 encoded string
    //getElem("imgSrc").innerHTML = imageData;
}

// Photo capture failure event - display error
function onFail(message) {
    alert('Failed because: ' + message);
}