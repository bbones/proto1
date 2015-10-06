/**
 * File salesorder.js
 * Created 12/06/15
 * @author Valentin Pogrebinsky
 */

define (["ordermod", "language", "uomUtil", "productUtil", "orgUnitUtil", "commonlib"], 
		function(ordermod, language, uomUtil, productUtil, orgUnitUtil, commonlib) {
	
	'use strict';

	function onLoad() {
				 
		$("#spa-cntr").on("orderSelected", function(event, orderId){
			if (typeof orderId !== 'undefined') {
				$("#bomf").form('load', '/protofront/service/boms/' + orderId + '?languageId=' +  language.id());
			}
		});
		
		$("#spa-cntr").on("savePressed", function(event) {
			$("#bomf").form('submit', {url : "/protofront/service/boms/?languageId=" + language.id()});
		});
		$("#spa-cntr").on("addPressed", function(event) {
			$("#bomf").form('clear');
		});
		$("#isdate").datebox({
			
			formatter:commonlib.dateFormatter,
			
			parser:commonlib.dateParser

		});
	}

	function initSales () {
		console.log("OrderMod loaded");
		ordermod.init({
			type : 'bom',
			serviceUrl : '/protofront/service/boms/',
			formUrl : '/protofront/forms/bom.html',
			onLoad : onLoad
		});
	}
	
	return {
		init : initSales
	};
});

