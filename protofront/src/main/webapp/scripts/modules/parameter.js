/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 */

define(['uomUtil', 'commonlib','edatagrid', 'language'], function(uomUtil, commonlib, edatagrid, language){
	'use strict';
	var initUOM = true;
	var currentParameterId = null; 
	
	function initParameterGrid() {
		$("#edgParameters").edatagrid({
			url : '/protofront/service/parameters/?languageId=' + language.id(),
			saveUrl : '/protofront/service/parameters/?languageId=' + language.id(),
			updateUrl : '/protofront/service/parameters/?languageId=' + language.id(),
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgParameters").edatagrid('addRow');
				},
				save : function(){
					$("#edgParameters").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgParameters").edatagrid('destroyRow');
				}

			}),

			onSelect : function(index, row) {
				console.log(row);
				if (row.parameterId !== null) {
					currentParameterId = row.parameterId;
					$("#edgNames").edatagrid({
						url : '/protofront/service/parameters/' + row.parameterId + '/names'

					});
					$.ajax('/protofront/service/parameters/' + row.parameterId + '/uoms/id').done(function (data) {
						console.log("=========");
						console.log(data);
						var d = $('#dlUOM').datalist('getData').rows;
						console.log(d);
						var l = d.length;
						initUOM = true;
						for ( var i=0; i<l; i++ ) {
							var indx = data.indexOf(d[i].id);
							if (indx !== -1) {
								$('#dlUOM').datagrid('checkRow', i);
							} else {
								$('#dlUOM').datagrid('uncheckRow', i);
							}
						}
						initUOM = false;
					});
				}
			}, // OnSelect
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/parameters/' + row.parameterId,
					method : "DELETE"
				});
			}

		});
		
	}
	
	function initNamesGrid() {
		$("#edgNames").edatagrid({
			saveUrl : '/protofront/service/parameters/names',
			updateUrl :'/protofront/service/parameters/names',
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgNames").edatagrid('addRow', {row : {parameterId : currentParameterId}});
				},
				save : function(){
					$("#edgNames").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgNames").edatagrid('destroyRow');
				}

			}),
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/parameters/names/' + row.parameterNameId,
					method : "DELETE"
				});
			}

		});
	}
	
	function initUOMS() {
		$('#dlUOM').datalist({
		    url: '/protofront/service/uoms/?languageId=' + language.id(),
		    onCheck : function(index, row) {
		    	if (!initUOM) {
		    		var par = $("#edgParameters").edatagrid('getSelected').parameterId;
		    		$.post('/protofront/service/parameters/' + par + '/uoms',{
		    			'uomId' : row.id
		    		});
		    	}
		    },
		    onUncheck : function(index, row) {
		    	if (!initUOM) {
		    		var par = $("#edgParameters").edatagrid('getSelected').parameterId;
		    		$.ajax({
		    			url : '/protofront/service/parameters/' + par + '/uoms' + row.id,
		    			method : 'DELETE'
		    		});
		    	}
		    }

		});
	}
	
	function init() {
		window.location.hash = "#parameter/"; 
		$("#spa-cntr").off();
		$.when(uomUtil.init(), language.init()).done(function() {
			$("#spa-cntr").panel({
				href : '/protofront/forms/parameter.html', 
				onLoad : function() {
					initParameterGrid();
					initNamesGrid();
					initUOMS();
				}
			});
		});
	}
	return {
		init : init
	};
});

