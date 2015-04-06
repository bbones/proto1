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
			updateUrl : "/protofront/service/requests/lang:" + IndexLib.lang(),
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
				$.ajax({
					url : "/protofront/service/requests/"+row.orderId,
					method : "DELETE"
				});
			}
//			,
//			onEndEdit : function(index,row,changes) {
//				debugger;
//				row.issueDate = new Date(row.issueDate).getTime();
//			}
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
        dateFormatter : function(value) {
        	var d = new Date(value);
        	return d.toLocaleDateString();
        },
        dateParser : function(s){
        	console.log(s);
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
        getChanges : function() {
        	debugger;
        	var r = $("#edgRequest").datagrid('getChanges');
        	console.log(r);
        }

	};
})();

