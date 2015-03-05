/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * TODO CRUD
 */

var PersonLib = (function() {
	
	function initPersonGrid() {
		$("#edgPerson").edatagrid({
			url : "/protofront/service/person/listbylang/" +
				$('#langSelector').combobox('getValue'),
			onSelect : function(index, row) {
				console.log(row);
				$("#edgNames").edatagrid({
					url : '/protofront/service/person/names/' + row.personId
				});
			} // OnSelect
		});
	};
	
	function initNameGrid() {
		$("#edgNames").edatagrid({});
	}

	return {
		init : function() {
			initPersonGrid();
			initNameGrid();
		}
	}
})();