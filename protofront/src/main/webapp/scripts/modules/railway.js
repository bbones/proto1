/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
/**
 * File railway.js Rsk 27.07.2015
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
		window.location.hash = "#railway/";
		$("#spa-cntr").off();
		$.when(language.init()).done(function() {
			$("#spa-cntr").panel({
				href : '/protofront/forms/railway.html',
				onLoad : function() {
					initRailwayGrid();
					initNameGrid();
				}
			});
		});
	}
	
	return {
		init : init
	};
});
