/**
 * 
 */

$("#orderDetail").panel({
	href : "/protofront/forms/purchaseOrder.html"
});

$("#orderAccordion").on('orderRecordChanged', function() {
	alert("Changed!");
});

$("#purchaseOrderForm").form({
	url : '/protofront/service/purchaseorders/',
	method : 'POST',
	onSubmit: function(){
		console.log('Submit');
	},
    success:function(data){
        alert(data);
    }
});

//$("#purchaseOrderForm").form('load', OrderLib.getOrderURL() + );
