/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
/**
 * TODO Show Receipt List
 * TODO External Receipt Search
 */

define (["language", "edatagrid"], function(language, edatagrid) {
	'use strict';

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