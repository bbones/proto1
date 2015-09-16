/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * 
 */

var ProductionOrderLib = (function() {
	
	function initPOGrid() {
		$("#edgProdOrder").edatagrid({
			url : "/protofront/service/prodorders/?languageId=" +
				IndexLib.lang(),
			method : 'GET',
			onSelect : function(index, row) {
				console.log(row);
				$("#edgLines").edatagrid({
					url : '/protofront/service/prodorders/'+ row.poId  +'/lines?languageId='
						+  IndexLib.lang() 
	
				});
			} // OnSelect edgProdOrder
		});
	};
	
	function initPOLinesGrid() {
		$("#edgLines").edatagrid({
			method : 'GET',
			onSelect : function(index, row) {
				console.log(row);
				$("#edgLineParameters").edatagrid({
					url : '/protofront/service/prodorders/lines/' +  row.orderLineId 
						+ '/lineparameters?languageId=' + IndexLib.lang()
						
				});
			} // OnSelect edgLines
		});
	}
	
	function initPOLineParam() {
		$("#edgLineParameters").edatagrid({
			method : 'GET'
		});
	}

	return {
		init : function() {
			initPOGrid();
			initPOLinesGrid();
			initPOLineParam();
		},
		addBOMs : function() {
			var poid = $("#edgProdOrder").edatagrid('getSelected').poId;
			$.ajax({
				url : '/protofront/service/prodorders/' + poid + '/createOrderBOMs' ,
				type: 'post'
			}).done(function() {
				alert("Success!");
			});
		}
	};
})();

