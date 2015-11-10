/*******************************************************************************
 * Copyright (C) 2015   Boris Efimenko
 *
 * mail:Boris.Efimenko@isd.com.ua
 * https://github.com/BorisEfimenko
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/

define([ 'currencyUtil', 'commonlib', 'edatagrid', 'easyui.form.ext', 'language' ], function(currencyUtil, commonlib,
		edatagrid, form, language) {

	'use strict';

	var roleMap = {};
	var currentContractID = {};
	var btnSearch = {};
	var dgSearchResult = {};
	var searchForm = {};
	var edgSides = {};
	var edgSupplement = {};
	var cf = {};

	function init() {
		btnSearch = $("#btnSearch");
		dgSearchResult = $("#dgSearchResult");
		searchForm = $("#searchForm");
		edgSides = $("#edgSides");
		edgSupplement = $("#edgSupplement");
		cf = $("#cf");
	}

	function getRoleMap() {
		return roleMap;
	}

	function position(contractID) {
		if ((typeof contractID) !== 'undefined') {
			currentContractID = contractID;
			edgSides.edatagrid({
				url : "/protofront/service/contracts/" + contractID + "/sides?languageId=" + language.id(),
				destroyUrl : "/protofront/service/contracts/sides/"
			});
			edgSupplement.edatagrid({
				url : "/protofront/service/contracts/" + contractID + "/supplements"
			});
			cf.form('load', '/protofront/service/contracts/' + contractID);
		}
	}

	function initSearchForm() {

		searchForm.form({
			url : '/protofront/service/contracts/findAll',
			ajax : true,
			success : function(data) {
				dgSearchResult.datagrid({
					data : data
				});
				// dgSearchResult.datagrid('load', data);
			}
		});
		btnSearch.click(function() {
			searchForm.form('submitAjax');
		});
		dgSearchResult.datagrid({
			onSelect : function(index, row) {
				position(row.id);
			},
			onDblClickRow : function(index, row) {
				$('#mainAcc').accordion('unselect', 0);
				$('#mainAcc').accordion('select', 1);
			}

		});

	}
	function initSidesGrid() {

		initRoleListAndMap();
		edgSides.edatagrid({
			saveUrl : "/protofront/service/contracts/sides?languageId=" + language.id(),
			updateUrl : "/protofront/service/contracts/sides?languageId=" + language.id(),
			toolbar : commonlib.edgmenu({
				add : function() {
					edgSides.edatagrid('addRow', {
						row : {
							contractId : currentContractID
						}
					});
				},
				save : function() {
					edgSides.edatagrid('getSelected').contractId = currentContractID;
					edgSides.edatagrid('saveRow');
				},
				destroy : function() {
					edgSides.edatagrid('destroyRow');
				}
			}),
			method : 'GET',

		});

	}

	function initSupplementGrid() {
		var supevent = jQuery.Event("contractSupplementSelected");

		$("#edgSupplement").edatagrid({
			toolbar : commonlib.edgmenu({
				add : function() {
					$("#edgSupplement").edatagrid('addRow');
					$("#csf").form('clear');
				},
				save : function() {
					console.log('submit supplement');
					console.dir($("#consupform"));
					$("#consupform").form('submit', {
						onSubmit : function(param) {
							param.contractId = currentContractID;
						},
						success : function(data) {
							var row = $("#edgSupplement").edatagrid('getSelected');
							var index = $("#edgSupplement").edatagrid('getRowIndex', row);

							$("#consupform").form('load', JSON.parse(data));
							$("#edgSupplement").edatagrid('updateRow', {
								index : index,
								row : JSON.parse(data)
							});
						} // Success
					});
				},
				destroy : function() {
					$("#edgSupplement").edatagrid('destroyRow');
				}

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
				$("#spa-cntr").trigger(supevent, row.id);

			}
		});

	}

	function initRoleListAndMap() {
		$.ajax('/protofront/service/contracts/roles?languageId=' + language.id()).done(function(dataArray) {
			var length = dataArray.length;
			for (var i = 0; i < length; i++) {
				roleMap[dataArray[i].srId] = dataArray[i].srName;
			}
			edgSides.datagrid('getColumnOption', 'roleId').editor.options.data = dataArray;
		});
	}

	function initContractForm() {

		$("#cf").form({
			url : '/protofront/service/contracts/',
			ajax : true,
			success : function(data) {
				// ???
			} // Success
		});

		$("#cfIsDate").datebox({
			formatter : commonlib.dateFormatter,
			parser : commonlib.dateParser
		});
	}

	function initContractSupplementForm() {
		$("#spa-cntr").on("contractSupplementSelected", function(event, contractSupplementId) {
			if (contractSupplementId) {
				$("#csf").form('load', '/protofront/service/contracts/supplements/' + contractSupplementId);
			}
		});

		$("#supisdate").datebox({

			formatter : commonlib.dateFormatter,

			parser : commonlib.dateParser

		});
		$("#currencyId").combogrid({
			panelWidth : 500,
			idField : 'numCode',
			textField : 'charCode',
			data : currencyUtil.getCurrencyList(),
			columns : [ [ {
				field : 'numCode',
				title : 'Code',
				width : 80
			}, {
				field : 'charCode',
				title : 'Char code',
				width : 120
			}, {
				field : 'sign',
				title : 'Sign',
				width : 80,
				align : 'right'
			} ] ],
			fitColumns : true
		});
	}

	return {
		init : function() {
			window.location.hash = "#contract_/";
			$("#spa-cntr").off();
			$.when(currencyUtil.init(), language.init()).done(function() {
				$("#spa-cntr").panel({
					href : '/protofront/forms/contract_.html',
					onLoad : function() {
						init();
						initSearchForm();
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
		roleEditor : function() {
			return {
				type : 'combobox',
				options : {
					valueField : 'srId',
					textField : 'srName',
					required : true
				}
			};
		},
		partyFormatter : function(value, row, index) {
			return row.partyName;
		},
		partyEditor : function() {
			return {
				type : 'combogrid',
				options : {
					url : '/protofront/service/masterdata/parties?languageId=' + language.id(),
					idField : 'partyId',
					// valueField:'partyId',
					textField : 'partyName',
					method : 'GET',
					required : true,
					panelWidth : 450,
					delay : 500,
					mode : 'remote',
					pagination : true,
					columns : [ [ {
						field : 'partyId',
						title : 'Code',
						width : 120,
						sortable : true
					}, {
						field : 'partyName',
						title : 'Name',
						width : 400,
						sortable : true
					} ] ]
				}
			};
		}
	};
});
