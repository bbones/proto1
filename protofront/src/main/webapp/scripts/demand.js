/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * 
 */

var DemandLib = (function(){
	
	var currentProduct = function() {
		return $("#dgProducts").datagrid('getSelected').id;
	};
	
	var initParameterGrid = function () {
		$("#dgParameters").datagrid({});
		
	};
	
	var initProductSelector = function() {
		$("#productType").combotree({
			url:'/protofront/service/productType/getByParentTypeIdByLanguageId/' + IndexLib.lang(),
			onSelect : function(record) {
				$("#dgProducts").datagrid({
					url : '/protofront/service/product/prodListByProdTypeIdByLanguageId/'
									+ record.id + '&' + IndexLib.lang(),
					onSelect : function(index, row) {
						console.log(row);
						$("#dgParameters").datagrid({
							url : '/protofront/service/product/parameters/'
										+ row.id + '&' + IndexLib.lang()
						});
					} // OnSelect
				}); // edatagrid

			}
		});
		$("#dgProducts").datagrid();
	};
	
	
	var initDemandGrid = function() {
		var selectedParams = $("#dgParameters").datagrid('getSelections');
		var paramList = new Array();
		var columnList = new Array();
		columnList.push({field:'ck',checkbox:true,width:10});
		for(var key in selectedParams) {
			paramList.push(selectedParams[key].parameterId);
			columnList.push({field:'FVD'+selectedParams[key].parameterId,title:selectedParams[key].parameterName,width:80});
		};
		columnList.push({field:'qnty',title:'Qnty',width:80});
		columnList.push({field:'olUOMName',title:'Qnty UOM',width:40});
		console.log(paramList);
		$.ajax({
			dataType: 'json',
			url:'/protofront/service/demand/getconsol/' + IndexLib.lang() + "&" + currentProduct(),
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
		var order = new Order();
		var selectedParams = $("#dgParameters").datagrid('getSelections');
		var selectedDemandRows = $("#dgDemand").datagrid('getSelections');
		for (var key in selectedDemandRows) {
			var orderLine = new OrderLine(
				null,
				currentProduct(), // product
				selectedDemandRows[key].qnty, // qnty, 
				selectedDemandRows[key].olUOMId, // 
				new Array(),
				order
			);
			for(var pk in selectedParams) {
				var param = new OrderLineParameter(
						null,
						selectedParams[pk].parameterId,
						selectedDemandRows[key]['F' + selectedParams[pk].parameterId],
						selectedDemandRows[key]['FUOM' + selectedParams[pk].parameterId]
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
			$.getScript("/protofront/scripts/ordermodel.js");
			initProductSelector();
			initParameterGrid();
		},
		showDemand : function() {
			initDemandGrid();
		},
		switchProductType : function() { // TODO Debug backdoor!!!
			$("#dgProducts").datagrid({
				url : '/protofront/service/product/prodListByProdTypeIdByLanguageId/'
								+ 5 + '&' + IndexLib.lang(),
				onSelect : function(index, row) {
					console.log(row);
					$("#dgParameters").datagrid({
						url : '/protofront/service/product/parameters/'
									+ row.id + '&' + IndexLib.lang()
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
})();