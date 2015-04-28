/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/

/**
 * File order.js
 * Created 17.04.15
 * Base order form 
 * 
 */

var OrderLib = (function(){
	var orderURL = null;
	
	function initOrderGrid() {
		
		$("#edgOrder").edatagrid({
			url : orderURL + '?languageId=' + IndexLib.lang(),
			saveUrl : orderURL + '?languageId=' + IndexLib.lang(),
			updateUrl : orderURL + '?languageId=' + IndexLib.lang(),
			method:'GET',
			onSelect : function(index, row) {
				if (row.orderId != null) {
					$(this).trigger('orderRecordChanged');
					$("#edgLines").edatagrid({
						url : orderURL  +  row.orderId + '/lines?languageId=' 
								+ IndexLib.lang()
					});
				} // if
			}, // OnSelect edgOrder
			onDestroy : function(index,row){
				$.ajax({
					url : orderURL + row.orderId,
					method : "DELETE"
				});
			}
		});
	};
	
	function initLinesGrid() {
		$("#edgLines").edatagrid({
			method:'GET',
			saveUrl : orderURL + 'lines?languageId=' + IndexLib.lang(),
			updateUrl : orderURL + 'lines?languageId=' + IndexLib.lang(),
			onSelect : function(index, row) {
				console.log(row);
				if (row.orderLineId != null) {
					$("#edgLineParameters").edatagrid({
						url : orderURL + 'lines/'+ row.orderLineId + 
							'/lineparameters?languageId=' + IndexLib.lang()
					});
				}
			}, // OnSelect edgLines

			onDestroy : function(index,row){
				$.ajax({
					url : orderURL + 'lines/' + row.orderLineId,
					method : "DELETE"
				});
			}
		});
	};
	
	
	function initLineParam() {
		$("#edgLineParameters").edatagrid({
			method:'GET'
		});
	};


	
	return {
		/**
		 * frame : string name of frame for display
		 * options : JS object { 
		 * 		url : Order Data URL
		 * 		orderFields : EasyUI datagrid field array,
		 *  
		 */
		init : function(frame, options) {
			$(frame).panel({
				href : '/protofront/forms/order.html',
				onLoad : function() {
					orderURL = options.orderURL; 
					initOrderGrid();
					initLinesGrid();
					initLineParam();
				}
			});
			$.getScript(options.scriptURL);
		},
        appendOrder : function() {
        	// $("#edgOrder").edatagrid('addRow');
        },
        acceptOrder : function() {
        	$("#edgOrder").edatagrid('saveRow');
        },

		button : function (value, row, index) {
            return '<input type="button" onclick="alert(' + row.olpId + ')" value="Add" class="GridButton"/>';
        },
        
        appendLine : function() {
        	$("#edgLines").edatagrid('addRow', {
        		row : {
        			orderId : $("#edgOrder").edatagrid('getSelected').orderId
        		}
        	});
        },
        acceptLine : function() {
        	$("#edgLines").edatagrid('saveRow');
		},
		removeLine : function() {
        	$("#edgLines").edatagrid('destroyRow');
			
		},
        appendParameter : function() {
        	$("#edgLineParameters").edatagrid('addRow', {
        		row : {
        			orderId : $("#edgLines").edatagrid('getSelected').olpId
        		}
        	});
        },
        acceptParameter : function() {
        	$("#edgLineParameters").edatagrid('saveRow');
		},
		removeParameter : function() {
        	$("#edgLineParameters").edatagrid('destroyRow');
			
		},
		getOrderURL : function() {
			return orderURL;
		}
	};
})();