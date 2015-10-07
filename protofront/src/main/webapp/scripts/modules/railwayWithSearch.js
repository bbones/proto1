/**
 * File railway.js Rsk 30.09.2015
 */

define([ 'commonlib', 'edatagrid', 'datagrid.excel','language' ], function(commonlib, edatagrid, datagrid, language) {
	'use strict';

	var currentRailwayId = null;

	function saveRailwaysToExcel() {
		$("#edgRailways").datagrid('toExcel', 'Railways');
	}
	
	function saveRailwayNamesToExcel() {
		$("#edgRailwayNames").datagrid('toExcel', 'RailwayNames');
	}
	
    function doSearch(){
    	console.log("Railway ID: " + $('#railwayid').val() + " Railway Code: " + $('#railwaycode').val());
    }
	
	function initRailwayGrid() {
		$("#edgRailways").edatagrid(
				{
					url : '/protofront/service/railways/?languageId='
							+ language.id(),
					saveUrl : '/protofront/service/railways/?languageId='
							+ language.id(),
					updateUrl : '/protofront/service/railways/?languageId='
							+ language.id(),
					toolbar : commonlib.edgmenu({
						add : function() {
							$("#edgRailways").edatagrid('addRow');
						},
						save : function() {
							$("#edgRailways").edatagrid('saveRow');
						},
						destroy : function() {
							$("#edgRailways").edatagrid('destroyRow');
						}
					}).concat([{iconCls: 'icon-save',
						handler: saveRailwaysToExcel,
				    	plain : true,
				    	text : 'Save to Excel'}]),
					onSelect : function(index, row) {
						//console.log("onSelectRailway");
						//console.log(row);
						currentRailwayId = row.id;
						if (row.id !== null) {
							$("#edgRailwayNames").edatagrid(
									{
										url : '/protofront/service/railways/'
												+ row.id + '/names',
									});
						}
					},
					onDestroy : function(index, row) {
						$.ajax({
							url : '/protofront/service/railways/' + row.id,
							method : "DELETE"
						});
					}
				});
	}

	function initNameGrid() {
		$("#edgRailwayNames").edatagrid({
			onDestroy : function(index, row) {
				$.ajax({
					url : '/protofront/service/railways/names/' + row.id,
					method : "DELETE"
				});
			},
			saveUrl : '/protofront/service/railways/names',
			updateUrl : '/protofront/service/railways/names',
			toolbar : commonlib.edgmenu({
				add : function() {
					$("#edgRailwayNames").edatagrid('addRow', {
						row : {
							railwayId : currentRailwayId
						}
					});
				},
				save : function() {
					$("#edgRailwayNames").edatagrid('saveRow');
				},
				destroy : function() {
					$("#edgRailwayNames").edatagrid('destroyRow');
				}
			}).concat([{iconCls: 'icon-save',
				handler: saveRailwayNamesToExcel,
		    	plain : true,
		    	text : 'Save to Excel'}]),
		});
	}
	
	function init() {
		window.location.hash = "#railwayWithSearch/";
		$("#spa-cntr").off();
		$.when(language.init()).done(function() {
			$("#spa-cntr").panel({
				href : '/protofront/forms/railwayWithSearch.html',
				onLoad : function() {
					initRailwayGrid();
					initNameGrid();
					$("#railwaySearchBtn").click(function() {
						doSearch();
					});
				}
			});
		});
	}
	
	return {
		init : init
	};
});
