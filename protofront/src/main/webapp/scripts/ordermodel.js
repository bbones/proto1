/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 */

function OrderLine(productId, qnty, dimUnitId, parameterList, orderLineId) {
	this.productId = productId;
	this.qnty = qnty;
	this.dimUnit = dimUnitId;
	this.parameterList = parameterList;
	this.orderLineId = orderLineId;
};

function Order() {
	this.orderId = null;
	this.orderNo = "NEW ORDER";
	this.orderLines = new Array();
	
	this.addOrderLine = function(orderLine) {
			this.orderLines.push(orderLine);
	}
};

function OrderLineParameter(pId, pValue, pDU) {
	this.pId = pId;
	this.pValue = pValue;
	this.pDU = pDU;
};