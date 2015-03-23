/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * 
 */

var BOMLib = (function(){
	return {
		init : function() {
			$("#edgBOM").edatagrid({
				url:"/protofront/service/bom/listbylang/" +	IndexLib.lang()
			});
			console.log("BOMLib initialized");
		}
	};
})();