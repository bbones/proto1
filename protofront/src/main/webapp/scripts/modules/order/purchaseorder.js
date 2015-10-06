/**
 * 
 */

define (["ordermod", "language", "uomUtil", "productUtil", "orgUnitUtil", "commonlib"], 
		function(ordermod, language, uomUtil, productUtil, orgUnitUtil, commonlib) {
	
	'use strict';

	function onLoad() {
				 
		$("#spa-cntr").on("orderSelected", function(event, orderId){
			if (typeof orderId !== 'undefined') {
				$("#purof").form('load', '/protofront/service/purchaseorders/' + orderId + '?languageId=' +  language.id());
			}
		});
		
		$("#spa-cntr").on("savePressed", function(event) {
			$("#purof").form('submit', {url : "/protofront/service/purchaseorders/?languageId=" + language.id()});
		});
		$("#spa-cntr").on("addPressed", function(event) {
			$("#purof").form('clear');
		});
		$("#isdate").datebox({
			
			formatter:commonlib.dateFormatter,
			
			parser:commonlib.dateParser

		});
	}

	function initSales () {
		console.log("OrderMod loaded");
		ordermod.init({
			type : 'purchaseorder',
			serviceUrl : '/protofront/service/purchaseorders/',
			formUrl : '/protofront/forms/purchaseOrder.html',
			onLoad : onLoad
		});
	}
	
	return {
		init : initSales
	};
});

