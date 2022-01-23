function errorDB(err) {
    console.log("Error processing SQL: " + err.code);
}

function errorCreateDB(err) {
    console.log("Error setting up DB: " + err.code);
}

function successDB(err) {
    console.log("SQL executed successfuly");
}

function successCreateDB(err) {
    console.log("DB Ready!");
}

// Create the database tables IF they do not already exist
function createDB(tx) {
	tx.executeSql('CREATE TABLE IF NOT EXISTS ASSET (id integer primary key, description, category, make, model, price, purchase_loc, purchase_method, purchase_date, image_src, invoice_src)');
	tx.executeSql('DROP TABLE IF EXISTS ASSET_CATEGORY');
	tx.executeSql('CREATE TABLE IF NOT EXISTS ASSET_CATEGORY (id integer primary key, category)');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (1, "Electronic Equipment")');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (2, "Jewellery")');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (3, "Motor Vehicles")');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (5, "Cell Phones")');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (6, "Sundry")');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (7, "Clothing")');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (8, "DVD Movies")');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (9, "Furniture")');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (10, "Cameras")');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (11, "Sports Equipment")');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (12, "Printers and Copiers")');
	tx.executeSql('INSERT INTO ASSET_CATEGORY (id, category) VALUES (13, "Computer Equipment")');
}

// Clear Assets table
function rebuildAssets(tx){
	tx.executeSql('DROP TABLE IF EXISTS ASSET');
	tx.executeSql('CREATE TABLE IF NOT EXISTS ASSET (id integer primary key, description, category, make, model, price, purchase_loc, purchase_method, purchase_date, image_src, invoice_src)');
}

function successDropAssets(){
	console.log("Assets Table Dropped");
	clearAssetList();
}

function queryCategories(tx){
	tx.executeSql('SELECT * FROM ASSET_CATEGORY', [], listCategories, errorDB);
}

function queryAssets(t){
	var catId = sessionStorage.categoryId;
	//t.executeSql('SELECT id, description, make FROM ASSET WHERE category = ' + catId, [], listAssets, errorDB);
	//t.executeSql('SELECT id, description, make FROM ASSET', [], listAssets, errorDB);
	t.executeSql('SELECT id, description, make, category FROM ASSET', [], listAssets, errorDB);
}

/* Save a new asset with all it's details
 */
function addSaveAsset(tx){
    var descVal = getElem("descIn").value;
    var catVal = getElem("categoryIn").value;
    var makeVal = getElem("makeIn").value;
    var modelVal = getElem("modelIn").value;
    var priceVal = getElem("priceIn").value;
    var purchLocVal = getElem("purchLocIn").value;
    var purchMethVal = getElem("purchMethIn").value;
    var purchDateVal = getElem("purchDateIn").value;
    var imageVal = getElem("imageIn").src;
    var invoiceVal = getElem("invoiceIn").src;
    
    var sSQL = 'INSERT INTO ASSET (description, category, make, model, price, purchase_loc, purchase_method, purchase_date, image_src, invoice_src) VALUES (';
    sSQL = sSQL + '"' + descVal + '",';
    sSQL = sSQL + '"' + catVal + '",';
    sSQL = sSQL + '"' + makeVal + '",';
    sSQL = sSQL + '"' + modelVal + '",';
    sSQL = sSQL + '"' + priceVal + '",';
    sSQL = sSQL + '"' + purchLocVal + '",';
    sSQL = sSQL + '"' + purchMethVal + '",';
    sSQL = sSQL + '"' + purchDateVal + '",';
    sSQL = sSQL + '"' + imageVal + '",';
    sSQL = sSQL + '"' + invoiceVal + '")';
    
    tx.executeSql(sSQL);
}

/* 	Save an existing asset's edited details
 *  id is not editable
 */
function editSaveAsset(tx){
	var id = sessionStorage.assetId;
	
	var descVal = getElem("descIn").value;
	var catVal = getElem("categoryIn").value;
    var makeVal = getElem("makeIn").value;
    var modelVal = getElem("modelIn").value;
    var priceVal = getElem("priceIn").value;
    var purchLocVal = getElem("purchLocIn").value;
    var purchMethVal = getElem("purchMethIn").value;
    var purchDateVal = getElem("purchDateIn").value;
    var imageVal = getElem("imageIn").src;
    var invoiceVal = getElem("invoiceIn").src;
    
    var sSQL = 'UPDATE ASSET SET ';
    sSQL = sSQL + 'description = "' + descVal + '",';
    sSQL = sSQL + 'category = "' + catVal + '",';
    sSQL = sSQL + 'make = "' + makeVal + '",';
    sSQL = sSQL + 'model = "' + modelVal + '",';
    sSQL = sSQL + 'price = "' + priceVal + '",';
    sSQL = sSQL + 'purchase_loc = "' + purchLocVal + '",';
    sSQL = sSQL + 'purchase_method = "' + purchMethVal + '",';
    sSQL = sSQL + 'purchase_date = "' + purchDateVal + '",';
    sSQL = sSQL + 'image_src = "' + imageVal + '",';
    sSQL = sSQL + 'invoice_src = "' + invoiceVal + '"';
    sSQL = sSQL + 'WHERE id = ' + id;
    
    tx.executeSql(sSQL);
}

function successAssetSaveDB(){
	getElem("outputSave").innerHTML = "Result: Saved";
	console.log("Asset Saved");
}

function queryAssetDet(tx){
	var astId = sessionStorage.assetId;
    tx.executeSql('SELECT * FROM ASSET WHERE id = ' + astId, [], showAssetDet, errorDB);
}