/**
 * File salesorder.js
 * Created 12/06/15
 * Author Valentin Pogrebinsky
 */

var SalesMod = (function() {
	function SalesOrder() {
		
	};
	
	var sales = null;
	
	function initSales () {
		console.log("initSales");
		$.getScript("/protofront/scripts/order/ordermod.js").done(function() {
			SalesOrder.prototype = OrderMod.getInstance();
			var salesOrder = new SalesOrder();
			salesOrder.load("/protofront/service/salesorders/");
		});
	};
	
	return {
		init : initSales
	};
})();

SalesMod.init();

