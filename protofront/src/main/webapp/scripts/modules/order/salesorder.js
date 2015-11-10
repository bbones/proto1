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



