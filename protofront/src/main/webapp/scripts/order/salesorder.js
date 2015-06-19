/**
 * File salesorder.js
 * Created 12/06/15
 * Author Valentin Pogrebinsky
 */

var SalesMod = (function() {
	function SalesOrder() {
		
	};
	
	var sales = null; 
	
	function afterLoad() {
		$("#orderDetails").panel({
			href : '/protofront/forms/salesOrder.html',
			onLoad : function() {
				$("#test").on("orderSelected", function(event, orderId){
					if (typeof orderId !== 'undefined') {
						$("#sof").form('load', '/protofront/service/salesorders/' + orderId + '?languageId=' +  IndexLib.lang());
					}
				});
				$("#isdate").datebox({
					
					formatter:IndexLib.dateFormatter,
					
					parser:IndexLib.dateParser

				});
			}
		});

	}
	
	
	function initSales () {
		console.log("initSales");
		$.getScript("/protofront/scripts/order/ordermod.js").done(function() {
			SalesOrder.prototype = OrderMod.getInstance();
			var salesOrder = new SalesOrder();
			salesOrder.load("/protofront/service/salesorders/", afterLoad);
		});
	};
	
	return {
		init : initSales
	};
})();

SalesMod.init();

