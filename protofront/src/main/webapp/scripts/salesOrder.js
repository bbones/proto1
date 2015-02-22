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
	
	function initAccord() {
		$("#aa").accordion({
			onSelect : function(title, index) {
				alert(title + index);
			}
		});
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