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
 * File ordermod.js
 * Created 12/06/15
 * Author Valentin Pogrebinsky
 */

define (['language', 'commonlib', 'edatagrid', 'uomUtil', 'productUtil'], function(language, commonlib, edatagrid, uomUtil, productUtil) {
	'use strict';

	var serviceURL = null;
	var currentOrderId = null;
	var currentOrderLineId = null;
	var options = null;
	
	function initOrderGrid() {
		console.log(options.serviceUrl);
		$("#edgOrder").edatagrid({
			url : options.serviceUrl + '?languageId=' + language.id(),
			saveUrl : options.serviceUrl + '?languageId=' + language.id(),
			updateUrl : options.serviceUrl + '?languageId=' + language.id(),
			method:'GET',
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgOrder").edatagrid('addRow', {row : {documentNo : "DOC", issueDate : new Date()}});
					var addPressed = jQuery.Event('addPressed');
					$("#spa-cntr").trigger(addPressed);
				},
				save : function(){
					var savePressed = jQuery.Event('savePressed');
					$("#spa-cntr").trigger(savePressed);
					// $("#edgOrder").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgOrder").edatagrid('destroyRow');
				}

			}),
			onSelect : function(index, row) {
				if (row.id !== null) {
					currentOrderId  = row.id;
					var event = jQuery.Event( 'orderSelected' );
					$("#spa-cntr").trigger(event, row.id);
				} // if
			}, // OnSelect edgOrder
			onDestroy : function(index,row){
				$.ajax({
					url : options.serviceUrl + row.id,
					method : "DELETE"
				});
			}
		});
	}
	
	function initLinesGrid() {
		$("#spa-cntr").on('orderSelected', 
			function(event, id) {
				console.log("Order Selected ->" + id);
				$("#edgLines").edatagrid({
					url : options.serviceUrl  +  id + '/lines?languageId=' 
							+ language.id()
				});
			}
		);
		
		$("#edgLines").edatagrid({
			method:'GET',
			saveUrl : options.serviceUrl + 'lines?languageId=' + language.id(),
			updateUrl : options.serviceUrl + 'lines?languageId=' + language.id(),
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgLines").edatagrid('addRow', {row : {orderId : currentOrderId}});
				},
				save : function(){
					$("#edgLines").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgLines").edatagrid('destroyRow');
				}

			}),
			onSelect : function(index, row) {
				if (row.orderLineId !== null) {
					currentOrderLineId = row.orderLineId;
					var event = jQuery.Event( 'orderLineSelected' );
					$("#spa-cntr").trigger(event, row.orderLineId);
				} // if
			}, // OnSelect edgLines

			onDestroy : function(index,row){
				$.ajax({
					url : options.serviceUrl + 'lines/' + row.orderLineId,
					method : "DELETE"
				});
			}
		});
	}
	
	function fillParameterTemplate() {
		console.log("Fill Parameter Template");
		$.ajax({
			url : options.serviceUrl + 'lines/' + currentOrderLineId + '/fillparameters',
			method : "POST",
			success : function() {
				$("#edgLineParameters").edatagrid('reload');
			}
		});
	}
	
	function initLineParam() {
		$("#spa-cntr").on('orderLineSelected', 
			function(event, olId) {
				$("#edgLineParameters").edatagrid({
					url : options.serviceUrl + 'lines/'+ olId + 
						'/lineparameters?languageId=' + language.id()
				});
			}
		);
		$("#edgLineParameters").edatagrid({
			method:'GET',
			saveUrl : options.serviceUrl + 'lines/parameters?languageId=' + language.id(),
			updateUrl : options.serviceUrl + 'lines/parameters?languageId=' + language.id(),
			toolbar : commonlib.edgmenu({
					add : function(){
						$("#edgLineParameters").edatagrid('addRow', {row : {orderLineId : currentOrderLineId}});
					},
					save : function(){
						$("#edgLineParameters").edatagrid('saveRow');
					},
					destroy : function(){
						$("#edgLineParameters").edatagrid('destroyRow');
					}

				}).concat([{iconCls: 'icon-add',
			    	handler: fillParameterTemplate,
			    	plain : true,
			    	text : 'Fill template'}]), // toolbar
			onDestroy : function(index,row){
				$.ajax({
					url : options.serviceUrl + 'lines/parameters/' + row.olpId,
					method : "DELETE"
				});
			},
			onBeforeEdit : function(index, row) {
				$.ajax({
					url : '/protofront/service/parameters/' + row.paramId + '/uoms',
					method : "GET",
					success : function(data) {
						var uoms = [];
						var length = data.length;
						for (var i = 0; i < length; i++) {
							uoms.push({
								id : data[i],
								shortName : uomUtil.getUomMap()[data[i]]
							});
						}
						var col = $("#edgLineParameters").datagrid('getColumnOption','uomId');
						col.editor = {
								type:'combobox',
						      	options:{
						           valueField:'id',
						           textField:'shortName',
						           data : uoms,
						           required:true
						       }
						};
					}
				});
				
			}

		});
	}

	function initForm() {
		$("#orderDetails").panel({
			href: options.formUrl,
			onLoad : options.onLoad
		});
	}
	
	function load() {
		console.log('Order.load');
		$("#spa-cntr").off();
		$.when(uomUtil.init(), language.init(), productUtil.init()).done(function() {
			$("#spa-cntr").panel({
				href : '/protofront/forms/order.html', 
				onLoad : function(){
					initOrderGrid();
					initLinesGrid();
					initLineParam();
					initForm();
					if (typeof options.onLoad != 'undefined') {
						options.onLoad();
					}
				}
			});
		});
	}

	return {
		init : function(opt) {
			options = opt;
			window.location.hash = "#" + options.type + "/";
			load();
		},
		button : function (value, row, index) {
            return '<input type="button" onclick="alert(' + row.olpId + ')" value="Add" class="GridButton"/>';
        }
	};
});

