/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
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
 * GNU v2 license text in root directory of project
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

