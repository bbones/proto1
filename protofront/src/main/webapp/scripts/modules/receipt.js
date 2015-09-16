/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * TODO Show Receipt List
 * TODO External Receipt Search
 */

define (["language", "edatagrid"], function(language, edatagrid) {
	var initReceiptGrid = function() {
		$("#edgReceipt").edatagrid({
			url : "/protofront/service/receipts/?languageId="  + language.id(),
			saveUrl : "/protofront/service/receipts/?languageId="  + language.id(),
			updateUrl : "/protofront/service/receipts/?languageId="  + language.id(),
			method : "GET",
			onSelect : function(index, row) {
				console.log(row);
				$("#edgIngredients").edatagrid({
					url : '/protofront/service/receipts/'+ row.receiptId + '/ingredients?languageId=' + language.id(), 
					saveUrl : "/protofront/service/receipts/" + row.receiptId + "/ingredients?languageId="  + language.id(),
					updateUrl : "/protofront/service/receipts/" + row.receiptId + "/ingredients?languageId="  + language.id()			
				});
				$("#edgByproducts").edatagrid({
					url : '/protofront/service/receipts/'+ row.receiptId + '/byproducts?languageId=' + language.id()
				});
			} // onSelect
		});
	};
	
	var initIngredGrid = function () {
		$("#edgIngredients").edatagrid({
			method : 'GET',
			onDestroy : function(index, row) {
				$.ajax({
					url : '/protofront/service/receipts/'+ row.receiptId + '/ingredients/' + row.receiptItemId,
					method : "DELETE"
				});
			}
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
		},
		removeIngredient : function() {
			$("#edgIngredients").edatagrid('destroyRow');
		}
	};
	
});