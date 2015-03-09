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
 * See also: ordermodel.js
 */
public class OrderLineDTO extends DTO {

	private static final long serialVersionUID = 2482598190978934721L;

	private Long orderLineId;
	private Long productId;
	private Double qnty;
	private Long uomId;
	private List<OrderLineParameterDTO> parameterList;

	public Long getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(Long orderLineId) {
		this.orderLineId = orderLineId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getQnty() {
		return qnty;
	}

	public void setQnty(Double qnty) {
		this.qnty = qnty;
	}

	public List<OrderLineParameterDTO> getParameterList() {
		return parameterList;
	}

	public void setParameterList(List<OrderLineParameterDTO> parameterList) {
		this.parameterList = parameterList;
	}

	public Long getUomId() {
		return uomId;
	}

	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}

}
