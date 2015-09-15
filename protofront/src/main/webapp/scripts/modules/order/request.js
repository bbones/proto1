/**
 * File request.js
 * Created 12/06/15
 * Author Valentin Pogrebinsky
 * TODO form plugin post injection
 */

define (["ordermod", "language", "uomUtil", "productUtil"], function(ordermod, language, uomUtil, productUtil) {

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
			data : ClientRepo.getOrgUnitList(),
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
	};
	
	return {
		init : initRequest
	};
});



