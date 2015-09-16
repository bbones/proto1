/**
 * 
 */

var PurchaseOrderLib = (function() {
	
	function saveOrder() {
		debugger;
		$("#purchaseOrderForm").form('submit', {
			url : '/protofront/service/purchaseorders/',
			// method : 'POST',
			onSubmit: function(param){
				console.log('Submit');
				param.languageId = IndexLib.lang();
			},
		    success:function(data){
		        alert(data);
		    }
		});
	};
	
	
	return {
		save : saveOrder,
		init : function init() {
			$.getScript("/protofront/scripts/order.js").done(function() {
				alert("PO");
			});
		}
	};
})();

$("#orderDetail").panel({
	href : "/protofront/forms/purchaseOrder.html"
});

$("#orderAccordion").on('orderRecordChanged', function() {
	alert("Changed!");
});


