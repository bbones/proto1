/**
 * File request.js
 * Created 12/06/15
 * Author Valentin Pogrebinsky
 */

var RequestMod = (function() {
	function RequestOrder() {
		
	};
	
	var sales = null; 
	
	function afterLoad() {
		$("#orderDetails").panel({
			href : '/protofront/forms/requestOrder.html',
			onLoad : function() {
				$("#test").on("orderSelected", function(event, orderId){
					if (typeof orderId !== 'undefined') {
						$("#req").form('load', '/protofront/service/requests/' + orderId + '?languageId=' +  IndexLib.lang());
					}
				});
				$("#isdate").datebox({
					
					formatter:IndexLib.dateFormatter,
					
					parser:IndexLib.dateParser

				});
			}
		});

	}
	
	
	function initRequest () {
		console.log("initRequest");
		$.getScript("/protofront/scripts/order/ordermod.js")
			.done(function() {
				console.log("OrderMod loaded");
				RequestOrder.prototype = OrderMod.getInstance();
				var requestOrder = new RequestOrder();
				requestOrder.load("/protofront/service/requests/");
			})
			.fail(function( jqxhr, settings, exception ) {
				debugger;
				console.log(exception);
			});
	};
	
	return {
		init : initRequest
	};
})();

RequestMod.init();
