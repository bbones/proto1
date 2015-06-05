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
			url : "/protofront/service/persons/?languageId=" +	IndexLib.lang(),
			method : 'GET',
			onSelect : function(index, row) {
				console.log(row);
				$("#edgNames").edatagrid({
					method : 'GET',
					url : '/protofront/service/persons/' + row.personId +'/names'
				});
			} // OnSelect
		});
	};
	
	function initNameGrid() {
		$("#edgNames").edatagrid({});
	}

	return {
		init : function() {
			$("#test").panel({
				href : '/protofront/forms/person.html', 
				onLoad : function() {
					initPersonGrid();
					initNameGrid();
				}
			});
		}
	}
})();

PersonLib.init();