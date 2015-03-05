/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/

package org.proto1.dto.order;

import java.util.List;

import org.proto1.dto.DTO;

/**
 * 
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public class OrderDTO extends DTO {

	private static final long serialVersionUID = -812432410799731830L;

	private Long orderId;
	private List<OrderLineDTO> orderLines;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public List<OrderLineDTO> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLineDTO> orderLines) {
		this.orderLines = orderLines;
	}
}
