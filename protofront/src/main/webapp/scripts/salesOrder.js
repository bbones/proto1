/**
 * 
 */

var SalesOrderLib = (function() {
	
	function initSOGrid() {
		$("#edgSalesOrder").edatagrid({
			url : "/protofront/service/salesOrder/listbylang/" +
				$('#langSelector').combobox('getValue'),
			onSelect : function(index, row) {
				console.log(row);
				$("#edgLines").edatagrid({
					url : '/protofront/service/enterprise/names/' + row.enterpriseId
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