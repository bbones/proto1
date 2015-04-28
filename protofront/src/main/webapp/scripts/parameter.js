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
			url : "/protofront/service/parameters/?languageId=" +
				$('#langSelector').combobox('getValue'),
			onSelect : function(index, row) {
				console.log(row);
				$("#edgNames").edatagrid({
					url : '/protofront/service/parameters/' + row.parameterId + '/names'
				});
			} // OnSelect
		});
		
	};
	
	function initNamesGrid() {
		$("#edgNames").edatagrid();
	};
	
	return {
		init : function() {
			initParameterGrid();
			initNamesGrid();
		}
	}
})();