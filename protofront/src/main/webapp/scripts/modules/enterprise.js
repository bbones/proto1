/**
 * File enterprise.js
 * Created 02.09.15
 * 
 */

define(["commonlib", "language", "edatagrid", "searchcom"],function(commonlib, language, edatagrid){
	'use strict';
	function initEnterpriseGrid() {
		var event = jQuery.Event( "contractSelected" );

		$("#entSearch").searchcom({
			frm : 'forms/searchform/enterpriseSrch.html',
			url : '/protofront/service/masterdata/parties?languageId=' + language.id(),
			grid : {
				columns:[[
			                  {field:'code',title:'Id#',width:100},
			                  {field:'name',title:'Name',width:100}
			    ]],
			    method : 'GET'
			}
		});
		
		$("#edgEnterprise").edatagrid({
			toolbar : commonlib.edgmenu({ 
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
				language.id(),
			saveUrl : "/protofront/service/enterprises/?languageId=" +
				language.id(),
			updateUrl : "/protofront/service/enterprises/?languageId=" +
				language.id(),
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
	}
	
	function initNameGrid() {
		$("#edgNames").edatagrid({
			method : 'GET',
			toolbar : commonlib.edgmenu({ 
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
			saveUrl : "/protofront/service/enterprises/names",
			updateUrl : "/protofront/service/enterprises/names",
			onDestroy : function(index, row) {
			   		$.ajax({
			   			url : '/protofront/service/enterprises/names/' + row.enterpriseNameId,
			   			method : 'DELETE'
			   		});
			}
		});
	}

	return {
		init: function () {
			window.location.hash = "#enterprise/";
			$("#spa-cntr").off();
			$("#spa-cntr").panel({
				href : '/protofront/forms/enterprise.html', 
				onLoad : function() {
					initEnterpriseGrid();
					initNameGrid();
				}
			});
		}
	};
});