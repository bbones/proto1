/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * 
 */

var ProductLib = (function(){
	
	function initProductTypeTree() {
		$("#productType").combotree({
			onSelect : function(record) {
				console.log(record);
				$("#edgProducts").edatagrid({
					url : '/protofront/service/products/types?productTypeId='
									+ record.id + '&languageId=' + IndexLib.lang(),
					saveUrl : '/protofront/service/products/?typeId=' + record.id + '&languageId=' + IndexLib.lang()
				}); // edatagrid
			}// OnSelect
		}); // Combotree
	}
	
	function initProductGrid() {
		$("#edgProducts").edatagrid({
			method : "GET",
			onSelect : function(index, row) {
				console.log(row);
				if (row.productId != null) {
					$("#edgNames").edatagrid({
						url : '/protofront/service/products/'
									+ row.productId + '/names'
					});
	
					$("#edgParameters").edatagrid({
						url : '/protofront/service/products/'+ row.productId + 
							'/parameters?languageId=' + IndexLib.lang(),
					});
				}
			} // OnSelect
		});
	}
	
	function  initNameGrid() {
		$("#edgNames").edatagrid({
			method : 'GET',
			saveUrl : '/protofront/service/products/names',
			updateUrl : '/protofront/service/products/names',
			destroyUrl : '/protofront/service/products/names'
		});
	}
	
	function initParameterGrid() {
		$("#edgParameters").edatagrid({
			method : 'GET',
			saveUrl : '/protofront/service/products/parameters',
			updateUrl : '/protofront/service/products/parameters',
			destroyUrl : '/protofront/service/products/parameters'
		});
	}
	
	
	return {
		init : function() {
			initProductTypeTree();
			initProductGrid();
			initNameGrid();
			initParameterGrid();
		},
		append : function() {
			$("#edgProducts").edatagrid('addRow', {
				row :{
					productTypeId : $("#productType").combotree('getValue')
				}
			});
		},
		save : function() {
			$("#edgProducts").edatagrid('saveRow');
		},
		
		appendName : function() {
			$("#edgNames").edatagrid('addRow', {
				row : {
					productId : $("#edgProducts").edatagrid('getSelected').productId
				}
			});
		},
		
		saveName : function() {
			$("#edgNames").edatagrid('saveRow');
		},
	
		removeName : function () {
			$("#edgNames").edatagrid('destroyRow');
		},
	
		removeParameter: function () {
			$("#edgParameters").edatagrid('destroyRow');
		},

		appendParameter : function () {
			var pid = $("#edgProducts").edatagrid('getSelected').id;
			$("#edgParameters").edatagrid('addRow', {
				row: {
					productId : $("#edgProducts").edatagrid('getSelected').productId,
					required :false
				}
			});
		},
	
		acceptParameter : function () {
			console.log($("#edgParameters").edatagrid('getSelected'));
			$("#edgParameters").edatagrid('saveRow');
		}
	}  //return
})();

