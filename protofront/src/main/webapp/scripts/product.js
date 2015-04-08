/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * 
 */

var ProductLib = (function(){
	
	function initProductGrid() {
		$("#edgProducts").edatagrid({});
	}
	
	function initProductTypeTree() {
		$("#productType").combotree({
			onSelect : function(record) {
				console.log(record);
				$("#edgProducts").edatagrid({
					url : '/protofront/service/products/productType='
									+ record.id + '&languageId=' + IndexLib.lang(),
					method : 'GET',
					onSelect : function(index, row) {
						console.log(row);
						$("#edgNames").edatagrid({
							url : '/protofront/service/products/names/'
										+ row.id
						});

						$("#edgParameters").edatagrid({
							url : '/protofront/service/products/parameters/'
										+ row.id + '&' + IndexLib.lang(),
						});
					} // OnSelect
				}); // edatagrid
			}// OnSelect
		}); // Combotree
	}
	
	function  initNameGrid() {
		$("#edgNames").edatagrid({
			saveUrl : '/protofront/service/products/saveName',
			destroyUrl : '/protofront/service/products/deleteName'
		});
	}
	
	function initParameterGrid() {
		$("#edgParameters").edatagrid({
			saveUrl : '/protofront/service/products/saveParameter',
			destroyUrl : '/protofront/service/products/deleteParameter'
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

