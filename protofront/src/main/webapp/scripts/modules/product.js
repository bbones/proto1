/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * TODO Delete Name request doesn't work
 */

define(["commonlib", "language", "uomUtil", "edatagrid"],function(commonlib, language, edatagrid, uomUtil){

	
	
	function initProductTypeTree() {
		$("#productType").combotree({
			onSelect : function(record) {
				console.log(record);
				$("#edgProducts").edatagrid({
					url : '/protofront/service/products/types?productTypeId='
									+ record.id + '&languageId=' + language.id()
				}); // edatagrid
			}// OnSelect
		}); // Combotree
	}
	
	function initProductGrid() {
		$("#edgProducts").edatagrid({
			saveUrl : '/protofront/service/products/?languageId=' + + language.id(),
			updateUrl : '/protofront/service/products/?languageId=' + + language.id(),
			method : "GET",
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgProducts").edatagrid('addRow', {row : {productTypeId : $("#productType").combotree('getValue')}});
				},
				save : function(){
					$("#edgProducts").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgProducts").edatagrid('destroyRow');
				}

			}),

			onSelect : function(index, row) {
				console.log(row);
				if (row.productId != null) {
					$("#edgNames").edatagrid({
						url : '/protofront/service/products/'
									+ row.productId + '/names'
					});
	
					$("#edgParameters").edatagrid({
						url : '/protofront/service/products/'+ row.productId + 
							'/parameters?languageId=' + language.id(),
					});
				}
			}, // OnSelect
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/products/' + row.productId,
					method : "DELETE"
				});
			}

		});
	}
	
	function  initNameGrid() {
		$("#edgNames").edatagrid({
			method : 'GET',
			saveUrl : '/protofront/service/products/names',
			updateUrl : '/protofront/service/products/names',
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgNames").edatagrid('addRow', {row : {productId : $("#edgProducts").edatagrid('getSelected').productId}});
				},
				save : function(){
					$("#edgNames").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgNames").edatagrid('destroyRow');
				}

			}),
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/products/names/' + row.nameId,
					method : "DELETE"
				});
			}

		});
	}
	
	function initParameterGrid() {
		$("#edgParameters").edatagrid({
			method : 'GET',
			saveUrl : '/protofront/service/products/parameters' + '?languageId=' + language.id(),
			updateUrl : '/protofront/service/products/parameters' + '?languageId=' + language.id(),
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgParameters").edatagrid('addRow', {row : {productId : $("#edgProducts").edatagrid('getSelected').productId}});
				},
				save : function(){
					$("#edgParameters").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgParameters").edatagrid('destroyRow');
				}

			}),
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/products/parameters/' + row.productParameterId,
					method : "DELETE"
				});
			}

		});
	}
	
	
	return {
		init : function() {
			window.location.hash = "#product/"; 
			$("#spa-cntr").off();
			$("#spa-cntr").panel({
				href : '/protofront/forms/product.html', 
				onLoad : function() {
					initProductTypeTree();
					initProductGrid();
					initNameGrid();
					initParameterGrid();
				}
			});

		}
	}  //return
});

