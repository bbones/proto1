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
					url : '/protofront/service/salesorder/lines/' + row.soId + '&' + 
						IndexLib.lang()
				});
			} // OnSelect
		});
	};
	
	function initSOLinesGrid() {
		$("#edgLines").edatagrid({});
	}

	return {
		init : function() {
			initSOGrid();
			initSOLinesGrid();
		}
	}
})();