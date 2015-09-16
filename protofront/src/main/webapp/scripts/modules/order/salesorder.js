/**
 * File salesorder.js
 * Created 12/06/15
 * @author Valentin Pogrebinsky
 */

define (["ordermod", "language", "uomUtil", "productUtil", "orgUnitUtil", "commonlib"], 
		function(ordermod, language, uomUtil, productUtil, orgUnitUtil, commonlib) {
	
	var sales = null; 
	
	function onLoad() {
		$("#orderDetails").panel({
			href : '/protofront/forms/salesOrder.html',
			onLoad : function() {
				 
				$("#spa-cntr").on("orderSelected", function(event, orderId){
					if (typeof orderId !== 'undefined') {
						$("#sof").form('load', '/protofront/service/salesorders/' + orderId + '?languageId=' +  language.id());
					}
				});
				
				$("#spa-cntr").on("savePressed", function(event) {
					$("#req").form('submit', {url : "/protofront/service/salesorders/?languageId=" + language.id()});
				});
				$("#spa-cntr").on("addPressed", function(event) {
					$("#req").form('clear');
				});
				$("#isdate").datebox({
					
					formatter:commonlib.dateFormatter,
					
					parser:commonlib.dateParser

				});
			},
			onLoadError : function(msg) {
				console.log('Error');
				console.log(msg);
			}
		});
	}

	function initSales () {
		console.log("OrderMod loaded");
		ordermod.init({
			type : 'salesorder',
			serviceUrl : '/protofront/service/salesorders/',
			formUrl : '/protofront/forms/salesOrder.html',
			onLoad : onLoad
		});
	};
	
	return {
		init : initSales
	};
});



