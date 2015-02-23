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
					url : '/protofront/service/salesorder/lines/' +  IndexLib.lang() + '&' + row.soId, 
					onSelect : function(index, row) {
						console.log(row);
						$("#edgLineParameters").edatagrid({
							url : '/protofront/service/salesorder/lineparameters/' + IndexLib.lang() + '&' + row.olId
								
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

