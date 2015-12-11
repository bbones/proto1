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
 * File enterprise.js
 * Created 02.09.15
 * 
 */

define(["commonlib", "language", "edatagrid", "searchcom","serializeObject"],function(commonlib, language, edatagrid){
	'use strict';
	function initEnterpriseGrid() {
		var event = jQuery.Event( "contractSelected" );

		$("#entSearch").searchcom({
			frm : 'forms/searchform/enterpriseSrch.html',
			url : '/protofront/service/enterprises/esearch?languageId=' + language.id(),
			grid : {
				columns:[[
			                  {field:'ORG_ID',title:'Id#',width:100},
			                  {field:'ORG_NAME',title:'Name',width:100},
			                  {field:'OKPO',title:'ESK Id',width:100}
			    ]],
			    method : 'POST'
			}
		});
		
		$("#edgEnterprise").edatagrid({
			toolbar : commonlib.edgmenu({ 
				add : function(){
					$("#edgEnterprise").edatagrid('addRow');
					$("#cf").form('clear');
				},
				save : function(){
					$("#edgEnterprise").edatagrid('saveRow');
				},
				destroy : function() {
					$("#edgEnterprise").edatagrid('destroyRow');
					
				}
			}),

			url : "/protofront/service/enterprises/?languageId=" +
				language.id(),
			saveUrl : "/protofront/service/enterprises/?languageId=" +
				language.id(),
			updateUrl : "/protofront/service/enterprises/?languageId=" +
				language.id(),
			method : 'GET',
			onSelect : function(index, row) {
				if (row.id) {
					$("#edgNames").edatagrid({
						url : '/protofront/service/enterprises/' + row.id +'/names'
					});
				}
			}, // OnSelect
			onDestroy : function(index, row) {
	    		$.ajax({
	    			url : '/protofront/service/enterprises/' + row.id,
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
					$("#edgNames").edatagrid('addRow');
				},
				save : function(){
					$("#edgNames").edatagrid('saveRow');
				},
				destroy : function() {
					$("#edgNames").edatagrid('destroyRow');
					
				}
			}),
			saveUrl : "/protofront/service/enterprises/names",
			updateUrl : "/protofront/service/enterprises/names",
			onDestroy : function(index, row) {
			   		$.ajax({
			   			url : '/protofront/service/enterprises/names/' + row.enterpriseNameId,
			   			method : 'DELETE'
			   		});
			}
		});
	}

	return {
		init: function () {
			window.location.hash = "#enterprise/";
			$("#spa-cntr").off();
			$("#spa-cntr").panel({
				href : '/protofront/forms/enterprise.html', 
				onLoad : function() {
					initEnterpriseGrid();
					initNameGrid();
				}
			});
		}
	};
});