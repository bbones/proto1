/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
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