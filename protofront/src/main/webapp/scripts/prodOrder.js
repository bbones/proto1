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
				$("#edgLines").edatagrid({
					url : '/protofront/service/salesorder/lines/' +  IndexLib.lang() + '&' + row.soId, 
					onSelect : function(index, row) {
						console.log(row);
						$("#edgLineParameters").edatagrid({
							url : '/protofront/service/salesorder/lineparameters/' + IndexLib.lang() + '&' + row.olId
								
						});
					} // OnSelect edgLines
	
				});
			} // OnSelect edgProdOrder
		});
	};
	
	function initPOLinesGrid() {
		$("#edgLines").edatagrid({});
	}
	
	function initAccord() {
//		$("#aa").accordion({
//			onSelect : function(title, index) {
//				alert(title + index);
//			}
//		});
	}
	
	function initSOLineParam() {
		$("#edgLineParameters").edatagrid();
	}

	return {
		init : function() {
			initAccord();
			initPOGrid();
			initPOLinesGrid();
			initPOLineParam();
		}
	}
})();

