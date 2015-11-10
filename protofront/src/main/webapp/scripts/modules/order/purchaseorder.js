/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
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

