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
					onSelect : function(index, row) {
						console.log(row);
						$("#edgNames").edatagrid({
							url : '/protofront/service/products/'
										+ row.id + '/names'
						});

						$("#edgParameters").edatagrid({
							url : '/protofront/service/products/'+ row.id + 
								'/parameters/?languageId=' + IndexLib.lang(),
						});
					} // OnSelect
				}); // edatagrid
			}// OnSelect
		}); // Combotree
	}
	
	function initProductGrid() {
		$("#edgProducts").edatagrid({method : "GET"});
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
		searchProductType : function() {
			var t = $('#productType').combotree('getTree');
			$('#productType').combotree('setValue', 3);
		},
	
		newProduct: function () {
			var ptId = $('#productType').combotree('getValue');
			if (ptId) {
				$.ajax({
					type : 'POST',
					url : '/protofront/service/products/getNewProduct',
					data : {
						productTypeId : ptId,
						languageId : $('#langSelector')
								.combobox('getValue')
					},
					success : function(respdata) {
						$("#edgProducts").edatagrid('addRow', {
							row : {
								id : respdata['id'],
								name : respdata['localizedProductName']
							}
						});
					},
					error : function(data, status, er) {
						alert("error: " + data + " status: " + status
								+ " er:" + er);
					}
			
				}); 
			} // if
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
			$("#edgParameters").edatagrid('addRow', {row: {productId : pid, required :false}});
		},
	
		acceptParameter : function () {
			console.log($("#edgParameters").edatagrid('getSelected'));
			$("#edgParameters").edatagrid('saveRow');
		}
	}  //return
})();

