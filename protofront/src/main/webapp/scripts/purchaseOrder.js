/**
 * 
 */

var PurchaseOrderLib = (function() {
	
	function saveOrder() {
		debugger;
		$("#purchaseOrderForm").form('submit', {
			url : '/protofront/service/purchaseorders/',
			// method : 'POST',
			onSubmit: function(){
				console.log('Submit');
			},
		    success:function(data){
		        alert(data);
		    }
		});
		
	};
	
	return {
		save : saveOrder
	};
})();

$("#orderDetail").panel({
	href : "/protofront/forms/purchaseOrder.html"
});

$("#orderAccordion").on('orderRecordChanged', function() {
	alert("Changed!");
});


