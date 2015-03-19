/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * 
 */

var SalesOrderLib = (function() {
	
	function initSOGrid() {
		$("#edgSalesOrder").edatagrid({
			url : "/protofront/service/salesorder/listbylang/" +
				IndexLib.lang(),
			onSelect : function(index, row) {
				console.log(row);
				$("#edgLines").edatagrid({
					url : '/protofront/service/salesorder/lines/' +  row.soId + '&' + IndexLib.lang(), 
					onSelect : function(index, row) {
						console.log(row);
						$("#edgLineParameters").edatagrid({
							url : '/protofront/service/salesorder/lineparameters/' + row.olId + '&' + IndexLib.lang()
								
						});
					} // OnSelect edgLines
	
				});
			} // OnSelect edgSalesOrder
		});
	};
	
	function initSOLinesGrid() {
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
			initSOGrid();
			initSOLinesGrid();
			initSOLineParam();
		}
	}
})();

