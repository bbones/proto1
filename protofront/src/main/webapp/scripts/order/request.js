/**
 * File ordermod.js
 * Created 12/06/15
 * Author Valentin Pogrebinsky
 */


var RequestMod = (function() {
	function RequestOrder() {
		
	};
	
	var request = null;
	
	function initRequest () {
		console.log("initRequest");
		$.getScript("/protofront/scripts/order/ordermod.js").done(function() {
			RequestOrder.prototype = OrderMod.getInstance();
			var requestOrder = new RequestOrder();
			requestOrder.load();
		});
	};
	
	return {
		init : initRequest
	};
})();

RequestMod.init();

