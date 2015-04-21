/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * TODO Show Receipt List
 * TODO External Receipt Search
 */

var ReceiptLib = (function() {
	
	var initReceiptGrid = function() {
		$("#edgReceipt").edatagrid({
			url : "/protofront/service/receipts/?languageId="  + IndexLib.lang(),
			method : "GET",
			onSelect : function(index, row) {
				console.log(row);
				$("#edgIngredients").edatagrid({
					url : '/protofront/service/receipts/'+ row.receiptId + '/ingredients?languageId=' + IndexLib.lang() 
				});
				$("#edgByproducts").edatagrid({
					url : '/protofront/service/receipts/'+ row.receiptId + '/byproducts?languageId=' + IndexLib.lang()
				});
			} // onSelect
		});
	};
	
	var initIngredGrid = function () {
		$("#edgIngredients").edatagrid({method : 'GET'});
	};
	
	var initByProdGrid= function () {
		$("#edgByproducts").edatagrid({method : 'GET'});
	};

	
	return {
		init : function() {
			initReceiptGrid(); 
			initIngredGrid();
			initByProdGrid();
		}
	};
	
})();