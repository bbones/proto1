/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 */

function OrderLine() {

};

function Order() {
	this.orderId = null;
	this.orderNo = "NEW ORDER";
	this.orderLines = [];
	
	return {
		addOrderLine : function(orderLine) {
			this.orderLines.put(orderLine);
		}
	}
};