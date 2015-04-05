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
			url : "/protofront/service/requests/lang:" + IndexLib.lang(),
			saveUrl : "/protofront/service/requests/lang:" + IndexLib.lang(),
			// destroyUrl : "/protofront/service/requests/",
			method:'GET',
			onSelect : function(index, row) {
				console.log(row);
//				$("#edgLines").edatagrid({
//					url : '/protofront/service/requests/'  +  row.soId + '/lines/lang:' + IndexLib.lang(), 
//					onSelect : function(index, row) {
//						console.log(row);
//						$("#edgLineParameters").edatagrid({
//							url : '/protofront/service/requests/lines/'+ row.olId + 
//								'/lineparameters/lang:' + IndexLib.lang()
//						});
//					} // OnSelect edgLines
//				});
			}, // OnSelect edgRequest
			onDestroy : function(index,row){
				debugger;
				alert(row.orderId);
			}
		});
	};
	
	function initLinesGrid() {
		$("#edgLines").edatagrid({});
	};
	
	
	function initLineParam() {
		$("#edgLineParameters").edatagrid();
	};

	return {
		init : function() {
			initRequestGrid();
			initLinesGrid();
			initLineParam();
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
        dateFormatter : function(value,row,index) {
        	var d=new Date(value);
        	return d.toLocaleDateString();
        	// return $.fn.datebox.defaults.formatter(d);

        },
        dateParser : function(s){
        	debugger;
        	if (typeof(s) == 'number')
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
        myformatter : function(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        },
        removeRequest : function() {
        	$("#edgRequest").edatagrid('destroyRow');
        }

	};
})();

