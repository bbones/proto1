/**
 * 
 */

var ParameterLib = (function(){
	
	function initParameterGrid() {
		$("#edgParameters").edatagrid({
			url : "/protofront/service/parameter/parameterListByLanguageId/" +
				$('#langSelector').combobox('getValue'),
			onSelect : function(index, row) {
				console.log(row);
				$("#edgNames").edatagrid({
					url : '/protofront/service/parameter/names/' + row.parameterId
				});
			} // OnSelect
		});
		
	}
	
	return {
		init : function() {
			initParameterGrid();
		}
	}
})();