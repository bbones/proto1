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
			url : "/protofront/service/receipt/listbylang/" + IndexLib.lang(),
			onSelect : function(index, row) {
				console.log(row);
				$("#edgIngredients").edatagrid({
					url : '/protofront/service/receipt/ingredients/' + IndexLib.lang() + "&" + row.receiptId
				});
				$("#edgByproducts").edatagrid({
					url : '/protofront/service/receipt/byproducts/' + IndexLib.lang() + "&" + row.receiptId
				});
			} // onSelect
		});
	}
	
	var initIngredGrid = function () {
		$("#edgIngredients").edatagrid();
	};
	
	var initByProdGrid= function () {
		$("#edgByproducts").edatagrid();
	};

	
	return {
		init : function() {
			initReceiptGrid(); 
			initIngredGrid();
			initByProdGrid();
		}
	}
	
})();