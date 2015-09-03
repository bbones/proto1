/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * TODO CRUD
 */

define(["commonlib", "language", "edatagrid"],function(commonlib, language, edatagrid){
	
	function initPersonGrid() {
		$("#edgPerson").edatagrid({
			url : "/protofront/service/persons/?languageId=" +	language.id(),
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
			window.location.hash = "#person/"; 
			$("#spa-cntr").off();
			$("#spa-cntr").panel({
				href : '/protofront/forms/person.html', 
				onLoad : function() {
					initPersonGrid();
					initNameGrid();
				}
			});
		}
	}
});

