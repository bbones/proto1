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
 * TODO Delete Name request doesn't work
 */

define(["jquery", "commonlib", "language", "uomUtil", "edatagrid", "parameterUtil"],
		function($, commonlib, language, uomUtil, edatagrid, parameterUtil){

	
	'use strict';

	function initProductTypeTree() {
		$("#productType").combotree({
			onSelect : function(record) {
				console.log(record);
				$("#edgProducts").edatagrid({
					url : '/protofront/service/products/types?productTypeId='
									+ record.id + '&languageId=' + language.id()
				}); // edatagrid
			}// OnSelect
		}); // Combotree
	}
	
	function initProductGrid() {
		$("#edgProducts").edatagrid({
			saveUrl : '/protofront/service/products/?languageId=' +  language.id(),
			updateUrl : '/protofront/service/products/?languageId=' +  language.id(),
			method : "GET",
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgProducts").edatagrid('addRow', {row : {productTypeId : $("#productType").combotree('getValue')}});
				},
				save : function(){
					$("#edgProducts").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgProducts").edatagrid('destroyRow');
				}

			}),

			onSelect : function(index, row) {
				console.log(row);
				if (row.productId !== null) {
					$("#edgNames").edatagrid({
						url : '/protofront/service/products/'
									+ row.productId + '/names'
					});
	
					$("#edgParameters").edatagrid({
						url : '/protofront/service/products/'+ row.productId + 
							'/parameters?languageId=' + language.id(),
					});
				}
			}, // OnSelect
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/products/' + row.productId,
					method : "DELETE"
				});
			}

		});
	}
	
	function  initNameGrid() {
		$("#edgNames").edatagrid({
			method : 'GET',
			saveUrl : '/protofront/service/products/names',
			updateUrl : '/protofront/service/products/names',
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgNames").edatagrid('addRow', {row : {productId : $("#edgProducts").edatagrid('getSelected').productId}});
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
					url : '/protofront/service/products/names/' + row.nameId,
					method : "DELETE"
				});
			}

		});
	}
	
	function initParameterGrid() {
		$("#edgParameters").edatagrid({
			method : 'GET',
			saveUrl : '/protofront/service/products/parameters' + '?languageId=' + language.id(),
			updateUrl : '/protofront/service/products/parameters' + '?languageId=' + language.id(),
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgParameters").edatagrid('addRow', {row : {productId : $("#edgProducts").edatagrid('getSelected').productId}});
				},
				save : function(){
					$("#edgParameters").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgParameters").edatagrid('destroyRow');
				}

			}),
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/products/parameters/' + row.productParameterId,
					method : "DELETE"
				});
			}

		});
	}
	
	function init() {
		window.location.hash = "#product/"; 
		$("#spa-cntr").off();
		$.when(uomUtil.init(), language.init(),parameterUtil.init()).done(function() {
			$("#spa-cntr").panel({
				href : '/protofront/forms/product.html', 
				onLoad : function() {
					initProductTypeTree();
					initProductGrid();
					initNameGrid();
					initParameterGrid();
				}
			});
		});
	
	}
	
	return {
		init : init
	};  //return
});

