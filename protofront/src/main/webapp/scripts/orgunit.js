/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * TODO CRUD
 */

var OrgUnitLib = (function() {
	var currentPartyId = null;
	var currentOUId = null;
	
	function initEnterpriseSelector() {
		$('#enterprise').combogrid({
      		url : '/protofront/service/enterprises/search?languageId=' + IndexLib.lang(),
      		idField : 'enterpriseId',
      		textField:'name',
      		method:'GET',
      		required:true,
      		panelWidth:450,
      	    delay: 500,
      	    mode: 'remote',
      	    pagination : true,
      		columns: [[
      		         {field:'id',title:'Code',width:120,sortable:true},
      		         {field:'name',title:'Name',width:400,sortable:true}
      		     ]],
      		onSelect : function(index, row) {
      			console.log(row);
      			currentPartyId = row.id;
      			var event = jQuery.Event( "enterpriseSelected" );
      			$("#test").trigger(event, row.id);
      		}
		});
	}
	
	function initMasterGrid() {
		$("#test").on("enterpriseSelected", function(event, enterpriseId){
			$("#edgMaster").edatagrid({
				url : '/protofront/service/orgunits/enterprise/' + enterpriseId + '?languageId=' + IndexLib.lang()
			}); 
		});
			

		$("#edgMaster").edatagrid({
			toolbar : IndexLib.edgmenu({ 
				add : function(){
					$("#edgMaster").edatagrid('addRow', {row : {enterpriseId : currentPartyId}});
					$("#cf").form('clear');
				},
				save : function(){
					$("#edgMaster").edatagrid('saveRow');
				},
				destroy : function() {
					$("#edgMaster").edatagrid('destroyRow');
					
				}
			}),

			url : "/protofront/service/orgunits/?languageId=" +
				IndexLib.lang(),
			saveUrl : "/protofront/service/orgunits/?languageId=" +
				IndexLib.lang(),
			updateUrl : "/protofront/service/orgunits/?languageId=" +
				IndexLib.lang(),
			method : 'GET',
			onSelect : function(index, row) {
				if (row.id) {
					if (typeof row.id != 'undefined') {
						currentOUId = row.id;
						var event = jQuery.Event( "orgUnitSelected" );
						$("#test").trigger(event, row.id);
						$("#edgNames").edatagrid({
							url : '/protofront/service/orgunits/' + row.id +'/names'
						});
					}
				}
			}, // OnSelect
			onDestroy : function(index, row) {
	    		$.ajax({
	    			url : '/protofront/service/orgunits/' + row.id,
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
					$("#edgNames").edatagrid('addRow', {row : {ouId : currentOUId}});
				},
				save : function(){
					$("#edgNames").edatagrid('saveRow');
				},
				destroy : function() {
					$("#edgNames").edatagrid('destroyRow');
					
				}
			}),
			saveUrl : "/protofront/service/orgunits/names",
			updateUrl : "/protofront/service/orgunits/names",
			onDestroy : function(index, row) {
			   		$.ajax({
			   			url : '/protofront/service/orgunits/names/' + row.id,
			   			method : 'DELETE'
			   		});
			}
		});
	}

	return {
		init : function() {
			$("#test").off();
			$("#test").panel({
				href : '/protofront/forms/orgunit.html', 
				onLoad : function() {
					initEnterpriseSelector();
					initMasterGrid();
					initNameGrid();
				}
			});
		}
	}
})();

OrgUnitLib.init();