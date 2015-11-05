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
/**
 * File request.js
 * Created 12/06/15
 * @author Valentin Pogrebinsky
 */

define (["language", "ordermodel", "uomUtil"], function(language, ordermodel, uomUtil){
	
	'use strict';

	var currentProduct = function() {
		return $("#dgProducts").datagrid('getSelected').productId;
	};
	
	var initParameterGrid = function () {
		$("#dgParameters").datagrid({
			method : 'GET'
			
		});
		
	};
	
	var initProductSelector = function() {
		$("#productType").combotree({
			url : '/protofront/service/productTypes/parents/?languageId=' + language.id(),
			method : 'GET',
			onSelect : function(record) {
				$("#dgProducts").datagrid({
					url : '/protofront/service/products/types?productTypeId='+ record.id + '&languageId=' + language.id()
				}); // edatagrid

			}
		});
		
		$("#dgProducts").datagrid({
			method : 'GET',
			onSelect : function(index, row) {
				console.log(row);
				$("#dgParameters").datagrid({
					url : '/protofront/service/products/' + row.productId + '/parameters?languageId=' + language.id()
				});
			} // OnSelect

		});
	};
	
	
	var initDemandGrid = function() {
		var selectedParams = $("#dgParameters").datagrid('getSelections');
		var paramList = [];
		var columnList = [];
		columnList.push({field:'ck',checkbox:true,width:10});
		for(var key in selectedParams) {
			paramList.push(selectedParams[key].productParameterId);
			columnList.push({field:'FVD'+selectedParams[key].productParameterId,title:selectedParams[key].parameterName,width:80});
		}
		columnList.push({field:'qnty',title:'Qnty',width:80});
		columnList.push({field:'olUOMName',title:'Qnty UOM',width:40});
		console.log(paramList);
		$.ajax({
			dataType: 'json',
			url:'/protofront/service/demand/getconsol/' + language.id() + "&" + currentProduct(),
			data: JSON.stringify(paramList), // {paramList : JSON.stringify(paramList)},
			type: 'post',
			contentType: 'application/json',
		    mimeType: 'application/json'
		}).done(function(result) {
			console.log(result);
			console.log(columnList);
			$("#dgDemand").datagrid({
				columns : [columnList],
				data : result,
				singleSelect : false
			});
		}); 
	};
	
	var collectData = function() {
		var order = new ordermodel.Order();
		var selectedParams = $("#dgParameters").datagrid('getSelections');
		var selectedDemandRows = $("#dgDemand").datagrid('getSelections');
		for (var key in selectedDemandRows) {
			var orderLine = new ordermodel.OrderLine(
				null,
				currentProduct(), // product
				selectedDemandRows[key].qnty, // qnty, 
				selectedDemandRows[key].olUOMId, // 
				[],
				order
			);
			for(var pk in selectedParams) {
				var param = new ordermodel.OrderLineParameter(
						null,
						selectedParams[pk].productParameterId,
						selectedDemandRows[key]['F' + selectedParams[pk].productParameterId],
						selectedDemandRows[key]['FUOM' + selectedParams[pk].productParameterId],
						null
					);
				orderLine.parameterList.push(param);
			}
			order.addOrderLine(orderLine);
		}
		
		console.log(order);
		return order;
	};
	
	
	var makeOrder = function(pod) {
		console.log("Making Order->");
		var order = collectData();
		
		$.ajax({
			dataType: 'json',
			url:'/protofront/service/demand/createProdOrder/',
			data: JSON.stringify(order), // {paramList : JSON.stringify(paramList)},
			type: 'post',
			contentType: 'application/json',
		    mimeType: 'application/json'
		}).done(function(result) {
			console.log("Done!");
			console.log(result);
			alert("Done!" + result);
		}); 

	};
	
	return {
		init : function() {
			window.location.hash = "#demand/"; 
			$("#spa-cntr").off();
			$.when(uomUtil.init(), language.init()).done(function() {
				$("#spa-cntr").panel({
					href : '/protofront/forms/demand.html', 
					onLoad : function() {
						initProductSelector();
						initParameterGrid();
					}
				});
			});
		},
		showDemand : function() {
			initDemandGrid();
		},
		switchProductType : function() { // TODO Debug backdoor!!!
			$("#dgProducts").datagrid({
				url : '/protofront/service/product/prodListByProdTypeIdByLanguageId/'
								+ 5 + '&' + language.id(),
				onSelect : function(index, row) {
					console.log(row);
					$("#dgParameters").datagrid({
						url : '/protofront/service/product/parameters/'
									+ row.id + '&' + language.id()
					});
				} // OnSelect
			}); // edatagrid
		},
		createProductionOrder : function () {
			makeOrder();
		},
		createPurchaseOrder : function() {
			console.log($("#dgDemand").datagrid('getSelections').length);
		}
	}; 
});