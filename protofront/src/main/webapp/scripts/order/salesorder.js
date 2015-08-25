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
			console.log("OrderMod loaded");
			OrderMod.init({
				url : '/protofront/service/salesorders/',
				onLoad : function() {
					$("#orderDetails").panel({
						href : '/protofront/forms/salesOrder.html',
						onLoad : function() {
							 
							$("#test").on("orderSelected", function(event, orderId){
								if (typeof orderId !== 'undefined') {
									$("#sof").form('load', '/protofront/service/salesorders/' + orderId + '?languageId=' +  IndexLib.lang());
								}
							});
							
							$("#test").on("savePressed", function(event) {
								$("#req").form('submit', {url : "/protofront/service/salesorders/?languageId=" + IndexLib.lang()});
							});
							$("#test").on("addPressed", function(event) {
								$("#req").form('clear');
							});
							$("#isdate").datebox({
								
								formatter:IndexLib.dateFormatter,
								
								parser:IndexLib.dateParser

							});
						},
						onLoadError : function(msg) {
							console.log('Error');
							console.log(msg);
						}
					});
				}
			});
		})
		.fail(function( jqxhr, settings, exception ) {
			console.log(exception);
		});
	};
	
	return {
		init : initSales
	};
})();

SalesMod.init();

