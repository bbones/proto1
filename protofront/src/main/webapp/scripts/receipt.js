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
			saveUrl : "/protofront/service/receipts/?languageId="  + IndexLib.lang(),
			updateUrl : "/protofront/service/receipts/?languageId="  + IndexLib.lang(),
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
		$("#edgIngredients").edatagrid({
			method : 'GET',
			saveUrl : "/protofront/service/receipts/ingredients?languageId="  + IndexLib.lang(),
			updateUrl : "/protofront/service/receipts/ingredients?languageId="  + IndexLib.lang()			
		});
	};
	
	var initByProdGrid= function () {
		$("#edgByproducts").edatagrid({method : 'GET'});
	};

	
	return {
		init : function() {
			initReceiptGrid(); 
			initIngredGrid();
			initByProdGrid();
		},
		appendIngredient : function() {
			$("#edgIngredients").edatagrid('addRow', {row : {receiptId : $("#edgReceipt").edatagrid('getSelected').receiptId}});
		},
		acceptIngredient : function() {
			$("#edgIngredients").edatagrid('saveRow');
		}
	};
	
})();