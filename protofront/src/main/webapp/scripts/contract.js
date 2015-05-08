/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * File contract.js
 * Created 17.04.15
 * 
 */

var ContractLib = (function(){
	var edgmenu = function(onClick) {
		return [
				    {
				    	iconCls: 'icon-add',
				    	handler: onClick.add,
				    	plain : true,
				    	text : 'New'
				    }, 
				    {
				    	iconCls: 'icon-save',
				    	handler: onClick.save,
				    	plain : true,
				    	text : 'Save'
				    }
				];
	};
	
	var roleMap = new Object();
	
	function getRoleMap () {
		return roleMap;
	}

	function initContractGrid() {
		$("#test").off();
		var event = jQuery.Event( "contractSelected" );
		$("#edgContract").edatagrid({
			toolbar : edgmenu({ 
				add : function(){
					$("#cf").form('clear');
					$("#edgSides").edatagrid('loadData', []);
					$("#edgSupplement").edatagrid('loadData', []);
				},
				save : function(){$("#cf").form('submit');}
			}),
			url : "/protofront/service/contracts/",
			method : 'GET',
			onSelect : function(index, row) {
				$("#test").trigger(event,row.id);
			} // OnSelect
		});
	};

	function initSidesGrid(){
		$("#test").on("contractSelected", function(event, contractId){
			$("#edgSides").edatagrid({
				url : "/protofront/service/contracts/" + contractId + "/sides?languageId=" + IndexLib.lang()
			});
		});
		initRoleListAndMap();
		$("#edgSides").edatagrid({
			toolbar : edgmenu({
					add : function(){console.log('add');},
					save : function(){console.log('save');}
			}),
			method : 'GET'
		});
		
	};

	function initSupplementGrid(){
		$("#test").on("contractSelected", function(event, contractId){
			$("#edgSupplement").edatagrid({
				url : "/protofront/service/contracts/" + contractId + "/supplements"
			});
		});
		$("#edgSupplement").edatagrid({
			toolbar : edgmenu({
					add : function(){console.log('add');},
					save : function(){console.log('save');}
			}),
			method : 'GET'
		});
		
	};

	function initRoleListAndMap() {
		$.ajax('/protofront/service/contracts/roles?languageId=' + IndexLib.lang()).done(function(dataArray) {
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				roleMap[dataArray[i].srId] = dataArray[i].srName;
			};
			$("#edgSides").datagrid('getColumnOption', 'roleId').editor.options.data = dataArray;
		});
	};

	function initContractForm() {
		$("#test").on("contractSelected", function(event, contractId){
			$("#cf").form('load', '/protofront/service/contracts/' + contractId);
		});
		$("#isdate").datebox();
	};
	
	return {
		init : function() {
			$("#test").panel({
				href : '/protofront/forms/contract.html', 
				onLoad : function() {
					initContractGrid();
					initContractForm();
					initSidesGrid();
					initSupplementGrid();
				}
			});
		},
        roleFormatter : function(value, row, index) {
        	return roleMap[value];
        },
        roleEditor: function() {
        	return {
	  			type:'combobox',
	          	options:{
	               valueField:'srId',
	               textField:'srName',
	               required:true
	           }
        	};
		}
	};
})();

ContractLib.init();
