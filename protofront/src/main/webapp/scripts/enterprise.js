/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * TODO CRUD
 */

var EnterpriseLib = (function() {
	
	function initEnterpriseGrid() {
		$("#edgEnterprise").edatagrid({
			url : "/protofront/service/enterprise/listbylang/" +
				IndexLib.lang(),
			onSelect : function(index, row) {
				console.log(row);
				$("#edgNames").edatagrid({
					url : '/protofront/service/enterprise/names/' + row.enterpriseId
				});
			} // OnSelect
		});
	};
	
	function initNameGrid() {
		$("#edgNames").edatagrid({});
	}

	return {
		init : function() {
			initEnterpriseGrid();
			initNameGrid();
		}
	}
})();