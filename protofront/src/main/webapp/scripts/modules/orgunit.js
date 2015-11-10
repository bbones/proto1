/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
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
/**
 * TODO CRUD
 */

define(['commonlib', 'language', 'edatagrid'], function(commonlib, language, edatagrid) {
	'use strict';

	var currentPartyId = null;
	var currentOUId = null;
	
	function initEnterpriseSelector() {
		$('#enterprise').combogrid({
      		url : '/protofront/service/enterprises/search?languageId=' + language.id(),
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
      			$("#spa-cntr").trigger(event, row.id);
      		}
		});
	}
	
	function initMasterGrid() {
		$("#spa-cntr").on("enterpriseSelected", function(event, enterpriseId){
			$("#edgMaster").edatagrid({
				url : '/protofront/service/orgunits/enterprise/' + enterpriseId + '?languageId=' + language.id()
			}); 
		});
			

		$("#edgMaster").edatagrid({
			toolbar : commonlib.edgmenu({ 
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

			url : "/protofront/service/orgunits/?languageId=" + language.id(),
			saveUrl : "/protofront/service/orgunits/?languageId=" + language.id(),
			updateUrl : "/protofront/service/orgunits/?languageId=" + language.id(),
			method : 'GET',
			onSelect : function(index, row) {
				if (row.id) {
					if (typeof row.id !== 'undefined') {
						currentOUId = row.id;
						var event = jQuery.Event( "orgUnitSelected" );
						$("#spa-cntr").trigger(event, row.id);
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
	}
	
	function initNameGrid() {
		$("#edgNames").edatagrid({
			method : 'GET',
			toolbar : commonlib.edgmenu({ 
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
	
	function init() {
		window.location.hash = "#orgunit/"; 
		$("#spa-cntr").off();
		$.when(language.init()).done(function() {
			$("#spa-cntr").panel({
	
				href : '/protofront/forms/orgunit.html', 
				onLoad : function() {
					initEnterpriseSelector();
					initMasterGrid();
					initNameGrid();
				}
			});
		});
	}

	return {
		init : init
	};
});
