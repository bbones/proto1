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

	var roleMap = new Object();
	
	function getRoleMap () {
		return roleMap;
	}

	function initContractGrid() {
		var event = jQuery.Event( "contractSelected" );
		$("#edgContract").edatagrid({
			toolbar : IndexLib.edgmenu({ 
				add : function(){
					$("#edgContract").edatagrid('addRow');
					$("#cf").form('clear');
					$("#edgSides").edatagrid('loadData', []);
					$("#edgSupplement").edatagrid('loadData', []);
				},
				save : function(){$("#cf").form('submit', {
				    success:function(data){
				    	var row = $("#edgContract").edatagrid('getSelected');
				    	var index = $("#edgContract").edatagrid('getRowIndex', row);
				    	console.log(index);
				    	
				    	$("#cf").form('load', JSON.parse(data));
				    	$("#edgContract").edatagrid('updateRow', {
				    		index : index,
				    		row : JSON.parse(data)});
				    } // Success
				})},
				destroy : function() {
					$("#edgContract").edatagrid('destroyRow');
				}	
				
			}),
			url : "/protofront/service/contracts/",
			method : 'GET',
			onSelect : function(index, row) {
				$("#test").trigger(event,row.id);
			}, // OnSelect
			onDestroy : function(index, row) {
				console.log(row);
	    		$.ajax({
	    			url : '/protofront/service/contracts/' + row.id,
	    			method : 'DELETE'
	    		});
			}

		});
	};

	function initSidesGrid(){
		$("#test").on("contractSelected", function(event, contractId){
			$("#edgSides").edatagrid({
				url : "/protofront/service/contracts/" + contractId + "/sides?languageId=" + IndexLib.lang(),
				saveUrl : "/protofront/service/contracts/sides?languageId=" + IndexLib.lang(),
				updateUrl : "/protofront/service/contracts/sides?languageId=" + IndexLib.lang(),
				onDestroy : function(index, row) {
					console.log(row);
		    		$.ajax({
		    			url : '/protofront/service/contracts/sides/' + row.id,
		    			method : 'DELETE'
		    		});
				}
			});
		});
		initRoleListAndMap();
		$("#edgSides").edatagrid({
			toolbar : IndexLib.edgmenu({
					add : function(){$("#edgSides").edatagrid('addRow');},
					save : function(){$("#edgSides").edatagrid('saveRow');},
					destroy : function(){$("#edgSides").edatagrid('destroyRow');}
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
			toolbar : IndexLib.edgmenu({
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
		$("#isdate").datebox({
			
			formatter:IndexLib.dateFormatter,
			
			parser:IndexLib.dateParser

		});
	};
	
	return {
		init : function() {
			$("#test").off();
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
		},
		partyFormatter : function (value, row, index) {
			return row.name;
		},
		partyEditor : function() {
			return {
	  			type:'combogrid',
	          	options:{
	          		url : '/protofront/service/masterdata/parties?languageId=' + IndexLib.lang(),
	          		valueField:'id',
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
	          		     ]]
	           }
			}
		}
	};
})();

ContractLib.init();
