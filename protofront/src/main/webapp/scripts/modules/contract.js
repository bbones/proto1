/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * File contract.js
 * Created 17.04.15
 * 
 */

define (['currencyUtil', 'commonlib', 'edatagrid'], 
		function(currencyUtil, commonlib, edatagrid){

	var roleMap = new Object();
	var currentContractId = {};
	
	function getRoleMap () {
		return roleMap;
	}

	function initContractGrid() {
		var event = jQuery.Event( "contractSelected" );
		$("#edgContract").edatagrid({
			toolbar : commonlib.edgmenu({ 
				add : function(){
					$("#edgContract").edatagrid('addRow');
					$("#cf").form('clear');
					$("#cf").form('load', { documentNo : "DOCNO", issueDate : new Date()});
					$("#edgSides").edatagrid('loadData', []);
					$("#edgSupplement").edatagrid('loadData', []);
				},
				save : function(){
					$('#cf').form('submitAjax');
				},
				destroy : function() {
					$("#edgContract").edatagrid('destroyRow');
				}	
				
			}),
			url : "/protofront/service/contracts/",
			method : 'GET',
			onSelect : function(index, row) {
				$("#spa-cntr").trigger(event,row.id);
				currentContractId = row.id;
			}, // OnSelect
			onDestroy : function(index, row) {
	    		$.ajax({
	    			url : '/protofront/service/contracts/' + row.id,
	    			method : 'DELETE'
	    		});
			}

		});
	};

	function initSidesGrid(){
		$("#spa-cntr").on("contractSelected", function(event, contractId){
			if ((typeof contractId) != 'undefined') {
				$("#edgSides").edatagrid({
					url : "/protofront/service/contracts/" + contractId + "/sides?languageId=" + language.id(),
					saveUrl : "/protofront/service/contracts/sides?languageId=" + language.id(),
					updateUrl : "/protofront/service/contracts/sides?languageId=" + language.id(),
					onDestroy : function(index, row) {
			    		$.ajax({
			    			url : '/protofront/service/contracts/sides/' + row.csId,
			    			method : 'DELETE'
			    		});
					}
				});
			}
		});
		initRoleListAndMap();
		$("#edgSides").edatagrid({
			toolbar : commonlib.edgmenu({
					add : function(){
						$("#edgSides").edatagrid('addRow', {row : {contractId : currentContractId}});
					},
					save : function(){
						$("#edgSides").edatagrid('getSelected').contractId = currentContractId;
						$("#edgSides").edatagrid('saveRow');
					},
					destroy : function(){$("#edgSides").edatagrid('destroyRow');}
			}),
			method : 'GET'
		});
		
	};

	function initSupplementGrid(){
		var supevent = jQuery.Event( "contractSupplementSelected" );

		$("#spa-cntr").on("contractSelected", function(event, contractId){
			if ((typeof contractId) != 'undefined') {
				$("#edgSupplement").edatagrid({
					url : "/protofront/service/contracts/" + contractId + "/supplements"
				});
			}
		});
		$("#edgSupplement").edatagrid({
			toolbar : commonlib.edgmenu({
					add : function(){
						$("#edgSupplement").edatagrid('addRow');
						$("#csf").form('clear');
					},
					save : function(){
						console.log('submit supplement');
						console.dir($("#consupform"));
						$("#consupform").form('submit', {
							onSubmit: function(param){
						        param.contractId = currentContractId;
						    },
						    success:function(data){
						    	var row = $("#edgSupplement").edatagrid('getSelected');
						    	var index = $("#edgSupplement").edatagrid('getRowIndex', row);
						    	
						    	$("#consupform").form('load', JSON.parse(data));
						    	$("#edgSupplement").edatagrid('updateRow', {
						    		index : index,
						    		row : JSON.parse(data)});
						    } // Success
						})
					},
					destroy : function(){$("#edgSupplement").edatagrid('destroyRow');}

			}),
			method : 'GET',
			onDestroy : function(index, row) {
	    		$.ajax({
	    			url : '/protofront/service/contracts/supplements/' + row.id,
	    			method : 'DELETE'
	    		});
			},
			onSelect : function(index, row) {
				console.log(row);
				$("#spa-cntr").trigger(supevent,row.id);

			}
		});
		
	};

	function initRoleListAndMap() {
		$.ajax('/protofront/service/contracts/roles?languageId=' + language.id()).done(function(dataArray) {
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				roleMap[dataArray[i].srId] = dataArray[i].srName;
			};
			$("#edgSides").datagrid('getColumnOption', 'roleId').editor.options.data = dataArray;
		});
	};

	function initContractForm() {
		$("#spa-cntr").on("contractSelected", function(event, contractId){
			if (typeof contractId !== 'undefined') {
				$("#cf").form('load', '/protofront/service/contracts/' + contractId);
			}
		});
		$("#cf").form({
			url : '/protofront/service/contracts/', 
			ajax : true,
			success:function(data){
		    	var row = $("#edgContract").edatagrid('getSelected');
		    	var index = $("#edgContract").edatagrid('getRowIndex', row);
		    	
		    	$("#cf").form('load', data);
		    	$("#edgContract").edatagrid('updateRow', {
		    		index : index,
		    		row : data});
		    } // Success
		});

		$("#isdate").datebox({
			
			formatter:commonlib.dateFormatter,
			
			parser:commonlib.dateParser

		});
	};
	
	function initContractSupplementForm() {
		$("#spa-cntr").on("contractSupplementSelected", function(event, contractSupplementId){
			debugger;
			if (contractSupplementId) {
				$("#csf").form('load', '/protofront/service/contracts/supplements/' + contractSupplementId);
			}
		});
		
		$("#supisdate").datebox({
			
			formatter:commonlib.dateFormatter,
			
			parser:commonlib.dateParser

		});
		$("#currencyId").combogrid({
			panelWidth: 500,
            idField: 'numCode',
            textField: 'charCode',
            data : currencyUtil.getCurrencyList(),
            columns: [[
                {field:'numCode',title:'Code',width:80},
                {field:'charCode',title:'Char code',width:120},
                {field:'sign',title:'Sign',width:80,align:'right'}
            ]],
            fitColumns: true
		});
	};
	
	return {
		init : function() {
			window.location.hash = "#contract/"; 
			$("#spa-cntr").off();
			$.when(currencyUtil.init(), language.init()).done(function() {
				$("#spa-cntr").panel({
					href : '/protofront/forms/contract.html', 
					onLoad : function() {
						initContractGrid();
						initContractForm();
						initSidesGrid();
						initSupplementGrid();
						initContractSupplementForm();
					}
				});
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
			return row.partyName;
		},
		partyEditor : function() {
			return {
	  			type:'combogrid',
	          	options:{
	          		url : '/protofront/service/masterdata/parties?languageId=' + language.id(),
	          		idField : 'partyId',
	          		// valueField:'partyId',
	          		textField:'partyName',
	          		method:'GET',
	          		required:true,
	          		panelWidth:450,
	          	    delay: 500,
	          	    mode: 'remote',
	          	    pagination : true,
	          		columns: [[
	          		         {field:'partyId',title:'Code',width:120,sortable:true},
	          		         {field:'partyName',title:'Name',width:400,sortable:true}
	          		     ]]
	           }
			}
		}
	};
});