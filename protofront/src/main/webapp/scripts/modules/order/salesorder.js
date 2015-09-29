/**
 * File salesorder.js
 * Created 12/06/15
 * @author Valentin Pogrebinsky
 */

define (["ordermod", "language", "uomUtil", "productUtil", "orgUnitUtil", "commonlib"], 
		function(ordermod, language, uomUtil, productUtil, orgUnitUtil, commonlib) {
	
	function onLoad() {
				 
		$("#spa-cntr").on("orderSelected", function(event, orderId){
			if (typeof orderId !== 'undefined') {
				$("#sof").form('load', '/protofront/service/salesorders/' + orderId + '?languageId=' +  language.id());
			}
		});
		
		$("#spa-cntr").on("savePressed", function(event) {
			$("#sof").form('submit', {url : "/protofront/service/salesorders/?languageId=" + language.id()});
		});
		$("#spa-cntr").on("addPressed", function(event) {
			$("#sof").form('clear');
		});
		$("#isdate").datebox({
			
			formatter:commonlib.dateFormatter,
			
			parser:commonlib.dateParser

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
	}
	
	return {
		init : initSales
	};
});



