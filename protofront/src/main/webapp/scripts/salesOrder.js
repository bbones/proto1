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
					url : '/protofront/service/salesorder/lines/' + IndexLib.lang() + '&' + row.soId
						
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