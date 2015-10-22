/**
 * File railway.js Rsk 30.09.2015
 */

define(	[ 'commonlib', 'edatagrid', 'datagrid.excel', 'easyui.form.ext', 'language' ],
	function(commonlib, edatagrid, datagrid, form, language) {
		'use strict';

		var currentRailwayId = null;

		function saveRailwaysToExcel() {
			$("#edgRailways").datagrid('toExcel', 'Railways');
		}

		function saveRailwayNamesToExcel() {
			$("#edgRailwayNames").datagrid('toExcel', 'RailwayNames');
		}

		function initRailwaySearhForm() {
			$("#fRailwaySearch").form({
				url : '/protofront/service/railways/findAll',
				ajax : true,
				success : function(data) {
				$("#edgRailwaysFound").edatagrid({
						data : data
					});
				}
			});
			$("#btnRailwaySearch").click(function() {
				$("#fRailwaySearch").form('submitAjax');
			});
			$("#edgRailwaysFound").edatagrid({
				onSelect : function(index, row) {
				// console.log("onSelectRailway");
				// console.log(row);
				$("#edgRailwayNames").datagrid('clearSelections');
				currentRailwayId = row.id;
				if (row.id !== null) {
					$("#fRailway").form(
						'load',	'/protofront/service/railways/'
							+ row.id);
					$("#edgRailwayNames").edatagrid({
						url : '/protofront/service/railways/' + row.id + '/names'
						});
					}
				}
			});
		}

		function initRailwayForm() {
			$("#fRailway").form({
				/*
				 * url : '/protofront/service/railways/', ajax: true
				 */
				/*
				 * saveUrl : '/protofront/service/railways/?languageId=' +
				 * language.id(), updateUrl :
				 * '/protofront/service/railways/?languageId=' + language.id(),
				 */
				/*
				 * onSelect : function(index, row) {
				 * //console.log("onSelectRailway"); //console.log(row);
				 * currentRailwayId = row.id; if (row.id !== null) {
				 * $("#edgRailwayNames").edatagrid( { url :
				 * '/protofront/service/railways/' + row.id + '/names', }); } }
				 *//*
					 * , onDestroy : function(index, row) { $.ajax({ url :
					 * '/protofront/service/railways/' + row.id, method :
					 * "DELETE" }); }
					 */
			});
			$("#btnRailwaySave").click(function() {
				//$("#fRailwaySearch").form('submitAjax');
				console.log("btnRailwaySaveClick");
			});
		}

		function initRailwayNameGrid() {
			$("#edgRailwayNames").edatagrid({
				onDestroy : function(index, row) {
					$.ajax({
						url : '/protofront/service/railways/names/'	+ row.id,
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
						}).concat([{
								iconCls : 'icon-save',
								handler : saveRailwayNamesToExcel,
								plain : true,
								text : 'Save to Excel'
						}]),
			});
		}

		function init() {
			window.location.hash = "#railwayWithSearch/";
			$("#spa-cntr").off();
			$.when(language.init()).done(function() {
				$("#spa-cntr").panel({
					href : '/protofront/forms/railwayWithSearch.html',
					onLoad : function() {
						initRailwaySearhForm();
						initRailwayForm();
						initRailwayNameGrid();
					}
				});
			});
		}

	return {
		init : init
	};
});
