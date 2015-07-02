/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * TODO CRUD
 */

var OrgUnitLib = (function() {
	var currentPartyId = null;
	
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
      		         {field:'partyId',title:'Code',width:120,sortable:true},
      		         {field:'partyName',title:'Name',width:400,sortable:true}
      		     ]],
      		onSelect : function(index, row) {
      			console.log(row);
      			currentPartyId = row.partyId;
      			var event = jQuery.Event( "partySelected" );
      			$("#test").tigger(event, row.partyId);
      		}
		});
	}
	
	function initMasterGrid() {

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
					$("#edgNames").edatagrid('addRow');
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