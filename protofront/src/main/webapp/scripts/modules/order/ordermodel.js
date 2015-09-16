/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * File ordermodel.js
 * Created 
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 */

define(function(){
	function Order() {
		this.orderId = null;
		this.orderNo = "NEW ORDER";
		this.orderLines = new Array();
		
		this.addOrderLine = function(orderLine) {
			orderLine.orderId = this.orderId;	
			this.orderLines.push(orderLine);
		};
	};

	function OrderLine(orderLineId, productId, qnty, uomId, parameterList, orderId) {
		this.orderLineId = orderLineId;
		this.productId = productId;
		this.qnty = qnty;
		this.uomId = uomId;
		this.parameterList = parameterList;
		this.orderId = orderId;
//		this.addOrderLineParameter(orderLineParameter) {
//			orderLineParameter.olId = this.orderLineId;
//			this.parameterList.push(orderLineParameter);
//		};
	};

	function OrderLineParameter(olpId, ppId, pValue, pUOM, olId) {
		this.orderLineParameterId = olpId;
		this.productParameterId = ppId;
		this.value = pValue;
		this.parameterUOM = pUOM;
		this.orderLineId = olId;
	};
	
	return {
		Order : Order,
		OrderLine : OrderLine,
		OrderLineParameter : OrderLineParameter
	}
});

