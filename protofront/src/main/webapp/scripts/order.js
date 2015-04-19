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
	return {
		/**
		 * frame : string name of frame for dispay
		 * options : JS object { 
		 * 		url : Order Data URL
		 * 		orderFields : EasyUI datagrid field array,
		 *  
		 */
		init : function(frame, options) {
			debugger;
			$(frame).panel('refresh', '/protofront/forms/order.html');
			$("#edgOrder").datagrid({url : options.url});
		}
	};
})();