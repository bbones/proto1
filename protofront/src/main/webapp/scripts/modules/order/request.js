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
 * File request.js
 * Created 12/06/15
 * @author Valentin Pogrebinsky
 */


define (["ordermod", "language", "uomUtil", "productUtil", "orgUnitUtil", 'commonlib'], 
		function(ordermod, language, uomUtil, productUtil, orgUnitUtil, commonlib) {

	'use strict';

	function onLoad() {
		 
		$("#spa-cntr").on("orderSelected", function(event, orderId){
			if (typeof orderId !== 'undefined') {
				$("#req").form('load', '/protofront/service/requests/' + orderId + '?languageId=' +  language.id());
			}
		});
		
		$("#spa-cntr").on("savePressed", function(event) {
			$("#req").form('submitAjax', {
				url : "/protofront/service/requests/?languageId=" + language.id(),
				success:function(data){
			    	var row = $("#edgOrder").edatagrid('getSelected');
			    	var index = $("#edgOrder").edatagrid('getRowIndex', row);
			    	
			    	$("#req").form('load', data);
			    	$("#edgOrder").edatagrid('updateRow', {
			    		index : index,
			    		row : data});
			    } // Success
			});
		});
		$("#spa-cntr").on("addPressed", function(event) {
			$("#req").form('clear');
		});
		$("#isdate").datebox({
			
			formatter:commonlib.dateFormatter,
			
			parser:commonlib.dateParser

		});
		$("#orgUnit").combobox({
			data : orgUnitUtil.getOrgUnitList(),
			valueField : 'id',
			textField : 'name'
		});
	}

	function initRequest () {
		console.log("initRequest");
		
		ordermod.init({
			type : 'request',
			serviceUrl : '/protofront/service/requests/',
			formUrl : '/protofront/forms/requestOrder.html',
			onLoad : onLoad
		});
	}
	
	return {
		init : initRequest
	};
});




