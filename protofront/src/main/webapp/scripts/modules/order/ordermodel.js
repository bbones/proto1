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
	'use strict';

	function Order() {
		this.id = null;
		this.documentNo = "NEW ORDER";
		this.lines = [];
		
		this.addOrderLine = function(orderLine) {
			orderLine.orderId = this.orderId;	
			this.lines.push(orderLine);
		};
	}

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
	}

	function OrderLineParameter(olpId, ppId, pValue, pUOM, olId, derivative) {
		this.olpId = olpId;
		this.prodParamId = ppId;
		this.olpValue = pValue;
		this.uomId = pUOM;
		this.olId = olId;
		this.derivative = derivative;
	}
	
	return {
		Order : Order,
		OrderLine : OrderLine,
		OrderLineParameter : OrderLineParameter
	};
});

