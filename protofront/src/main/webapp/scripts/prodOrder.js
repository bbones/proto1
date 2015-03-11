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
			url : "/protofront/service/prodorder/listbylang/" +
				IndexLib.lang(),
			onSelect : function(index, row) {
				console.log(row);
				debugger;
				$("#edgLines").edatagrid({
					url : '/protofront/service/prodorder/lines/' +  IndexLib.lang() + '&' + row.poId, 
					onSelect : function(index, row) {
						console.log(row);
						$("#edgLineParameters").edatagrid({
							url : '/protofront/service/prodorder/lineparameters/' + IndexLib.lang() + '&' + row.olId
								
						});
					} // OnSelect edgLines
	
				});
			} // OnSelect edgProdOrder
		});
	};
	
	function initPOLinesGrid() {
		$("#edgLines").edatagrid({});
	}
	
	function initPOLineParam() {
		$("#edgLineParameters").edatagrid();
	}

	return {
		init : function() {
			initPOGrid();
			initPOLinesGrid();
			initPOLineParam();
		}
	}
})();

