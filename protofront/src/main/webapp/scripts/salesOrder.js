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
			url : "/protofront/service/salesorders/?languageId=" +
				IndexLib.lang(),
			method : 'GET',
			onSelect : function(index, row) {
				console.log(row);
				$("#edgLines").edatagrid({
					url : '/protofront/service/salesorders/'  +  row.soId + '/lines?languageId=' + IndexLib.lang(), 
				});
			} // OnSelect edgSalesOrder
		});
	};
	
	function initSOLinesGrid() {
		$("#edgLines").edatagrid({
			method : 'GET',
			onSelect : function(index, row) {
				console.log(row);
				$("#edgLineParameters").edatagrid({
					url : '/protofront/service/salesorders/lines/' + row.orderLineId + '/lineparameters?languageId=' + IndexLib.lang()
						
				});
			} // OnSelect edgLines
		});
	};
	
	function initSOLineParam() {
		$("#edgLineParameters").edatagrid({
			method : 'GET'
		});
	};

	return {
		init : function() {
			initSOGrid();
			initSOLinesGrid();
			initSOLineParam();
		},
		button : function (value, row, index) {
            return '<input type="button" onclick="alert(' + row.olpId + ')" value="Add" class="GridButton"/>';
        }
	};
})();

