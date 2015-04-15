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
			url : "/protofront/service/boms/?languageId=" +
				IndexLib.lang(),
			method : 'GET',
			onSelect : function(index, row) {
				console.log(row);
				$("#edgLines").edatagrid({
					url : '/protofront/service/boms/' + row.bomId  + '/lines?languageId=' +  IndexLib.lang(), 
	
				});
			} // OnSelect edgProdOrder
		});
	};
	
	function initPOLinesGrid() {
		$("#edgLines").edatagrid({
			method : 'GET',
			onSelect : function(index, row) {
				console.log(row);
				$("#edgLineParameters").edatagrid({
					url : '/protofront/service/boms/lines/' +  row.orderLineId 
						+ '/lineparameters?languageId=' + IndexLib.lang()
				});
			} // OnSelect edgLines

		});
	}
	
	function initPOLineParam() {
		$("#edgLineParameters").edatagrid({
			method : 'GET'
		});
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