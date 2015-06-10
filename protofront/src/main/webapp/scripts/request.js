/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * 
 */

var RequestLib = (function() {
	
	function initRequestGrid() {
		
		$("#edgRequest").edatagrid({
			url : "/protofront/service/requests/?languageId=" + IndexLib.lang(),
			saveUrl : "/protofront/service/requests/?languageId=" + IndexLib.lang(),
			updateUrl : "/protofront/service/requests/?languageId=" + IndexLib.lang(),
			method:'GET',
			onSelect : function(index, row) {
				$("#edgLines").edatagrid({
					url : '/protofront/service/requests/'  +  row.orderId + '/lines?languageId=' 
							+ IndexLib.lang()
				});
			}, // OnSelect edgRequest
			onDestroy : function(index,row){
				$.ajax({
					url : "/protofront/service/requests/"+row.orderId,
					method : "DELETE"
				});
			}
		});
	};
	
	function initLinesGrid() {
		$("#edgLines").edatagrid({
			method:'GET',
			saveUrl : "/protofront/service/requests/lines?languageId=" + IndexLib.lang(),
			updateUrl : "/protofront/service/requests/lines?languageId=" + IndexLib.lang(),
			onSelect : function(index, row) {
				console.log(row);
				$("#edgLineParameters").edatagrid({
					url : '/protofront/service/requests/lines/'+ row.orderLineId + 
						'/lineparameters?languageId=' + IndexLib.lang()
				});
			}, // OnSelect edgLines

			onDestroy : function(index,row){
				$.ajax({
					url : "/protofront/service/requests/lines/"+row.orderLineId,
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
		init : function() {
			$.getScript("/protofront/scripts/order.js").done(function() {
				OrderLib.init("#test", {orderURL : "/protofront/service/requests/"})
			});
		},
		button : function (value, row, index) {
            return '<input type="button" onclick="alert(' + row.olpId + ')" value="Add" class="GridButton"/>';
        },
        appendRequest : function() {
        	$("#edgRequest").edatagrid('addRow');
        },
        acceptRequest : function() {
        	$("#edgRequest").edatagrid('saveRow');
        },
        dateFormatter : function(value) {
        	var d = new Date(value);
        	return d.toLocaleDateString();
        },
        dateParser : function(s){
        	if (!isNaN(s))
        		return new Date(s);
            if (!s) return new Date();
            var ss = (s.split('.'));
            var d = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var y = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        },
        removeRequest : function() {
        	$("#edgRequest").edatagrid('destroyRow');
        },
        appendLine : function() {
        	$("#edgLines").edatagrid('addRow', {
        		row : {
        			orderId : $("#edgRequest").edatagrid('getSelected').orderId
        		}
        	});
        },
        acceptLine : function() {
        	$("#edgLines").edatagrid('saveRow');
		},
		removeLine : function() {
        	$("#edgLines").edatagrid('destroyRow');
			
		},
		productFormatter : function(value,row){
			return row.productName;
		},
		uomFormatter : function(value,row){
			return row.uomShortName;
		}

	};
})();

RequestLib.init();

