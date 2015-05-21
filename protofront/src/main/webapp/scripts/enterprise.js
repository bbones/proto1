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
					$("#edgEnterprise").edatagrid('saveRow');
				},
				destroy : function() {
					$("#edgEnterprise").edatagrid('destroyRow');
					
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
			}, // OnSelect
			onDestroy : function(index, row) {
	    		$.ajax({
	    			url : '/protofront/service/enterprises/' + row.id,
	    			method : 'DELETE'
	    		});
			}
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
				},
				destroy : function() {
					$("#edgNames").edatagrid('destroyRow');
					
				}
			}),
			url : "/protofront/service/enterprises/names",
			saveUrl : "/protofront/service/enterprises/names",
			updateUrl : "/protofront/service/enterprises/names",
			onDestroy : function(index, row) {
			   		$.ajax({
			   			url : '/protofront/service/enterprises/names/' + row.id,
			   			method : 'DELETE'
			   		});
			}
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