/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
/**
 * File prodorder.js
 * Created 12/06/15
 * @author Valentin Pogrebinsky
 */


define (["ordermod", "language", "uomUtil", "productUtil", "orgUnitUtil", "commonlib"], 
		function(ordermod, language, uomUtil, productUtil, orgUnitUtil, commonlib) {
	
	'use strict';

	var sales = null; 
	
	function onLoad() {
				 
		$("#spa-cntr").on("orderSelected", function(event, orderId){
			if (typeof orderId !== 'undefined') {
				$("#prof").form('load', '/protofront/service/prodorders/' + orderId + '?languageId=' +  language.id());
			}
		});
		
		$("#spa-cntr").on("savePressed", function(event) {
			$("#prof").form('submit', {url : "/protofront/service/prodorders/?languageId=" + language.id()});
		});
		$("#spa-cntr").on("addPressed", function(event) {
			$("#prof").form('clear');
		});
		$("#isdate").datebox({
			
			formatter:commonlib.dateFormatter,
			
			parser:commonlib.dateParser

		});
	}

	function initProd () {
		console.log("OrderMod loaded");
		ordermod.init({
			type : 'prodorder',
			serviceUrl : '/protofront/service/prodorders/',
			formUrl : '/protofront/forms/prodOrder.html',
			onLoad : onLoad
		});
	}
	
	function addBOMs() {
		var poid = $("#edgProdOrder").edatagrid('getSelected').id;
		$.ajax({
			url : '/protofront/service/prodorders/' + poid + '/createOrderBOMs' ,
			type: 'post'
		}).done(function() {
			alert("Success!");
		});
	}
	
	return {
		init : initProd,
		addBOMs : addBOMs
	};
});

