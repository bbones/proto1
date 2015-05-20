/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * TODO CRUD
 */

var EnterpriseLib = (function() {
	
	function initEnterpriseGrid() {
		var event = jQuery.Event( "contractSelected" );

		$("#edgEnterprise").edatagrid({
			toolbar : IndexLib.edgmenu({ 
				add : function(){
					$("#edgEnterprise").edatagrid('addRow');
					$("#cf").form('clear');
				},
				save : function(){
					$("#edgEnterprise").edatagrid('addRow');
				}
			}),

			url : "/protofront/service/enterprises/?languageId=" +
				IndexLib.lang(),
			saveUrl : "/protofront/service/enterprises/?languageId=" +
				IndexLib.lang(),
			updateUrl : "/protofront/service/enterprises/?languageId=" +
				IndexLib.lang(),
			method : 'GET',
			onSelect : function(index, row) {
				if (row.id) {
					$("#edgNames").edatagrid({
						url : '/protofront/service/enterprises/' + row.id +'/names'
					});
				}
			} // OnSelect
		});
	};
	
	function initNameGrid() {
		$("#edgNames").edatagrid({
			method : 'GET',
			toolbar : IndexLib.edgmenu({ 
				add : function(){
					$("#edgNames").edatagrid('addRow');
				},
				save : function(){
					$("#edgNames").edatagrid('saveRow');
				}
			})
		});
	}

	return {
		init : function() {
			$("#test").off();
			$("#test").panel({
				href : '/protofront/forms/enterprise.html', 
				onLoad : function() {
					initEnterpriseGrid();
					initNameGrid();
				}
			});
		}
	}
})();

EnterpriseLib.init();