/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * 
 */

var BOMLib = (function(){
	function initBOMGrid() {
		$("#edgBOM").edatagrid({
			url : "/protofront/service/bom/listbylang/" +
				IndexLib.lang(),
			onSelect : function(index, row) {
				console.log(row);
				$("#edgLines").edatagrid({
					url : '/protofront/service/bom/lines/' + row.bomId  + '&' +  IndexLib.lang(), 
					onSelect : function(index, row) {
						console.log(row);
						$("#edgLineParameters").edatagrid({
							url : '/protofront/service/bom/lineparameters/' +  row.olId + '&' + IndexLib.lang()
								
						});
					} // OnSelect edgLines
	
				});
			} // OnSelect edgProdOrder
		});
	};
	
	function initPOLinesGrid() {
		$("#edgLines").edatagrid({});
	}
	
	function initPOLineParam() {
		$("#edgLineParameters").edatagrid();
	}


	return {
		init : function() {
			initBOMGrid();
			initPOLinesGrid();
			initPOLineParam();
			console.log("BOMLib initialized");
		}
	};
})();