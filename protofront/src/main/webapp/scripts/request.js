/**
 * 
 */

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
			url : "/protofront/service/requests/lang:" +
				IndexLib.lang(),
			saveUrl : "/protofront/service/requests/",
			method:'GET',
			onSelect : function(index, row) {
				console.log(row);
				$("#edgLines").edatagrid({
					url : '/protofront/service/requests/'  +  row.soId + '/lines/lang:' + IndexLib.lang(), 
					onSelect : function(index, row) {
						console.log(row);
						$("#edgLineParameters").edatagrid({
							url : '/protofront/service/requests/lines/'+ row.olId + 
								'/lineparameters/lang:' + IndexLib.lang()
						});
					} // OnSelect edgLines
				});
			} // OnSelect edgRequest
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
        }
	};
})();

