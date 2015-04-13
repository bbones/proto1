/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.dto.order;

import org.proto1.dto.DTO;

/**
 * 
 * @author Valentin Pogrebinsky (pva@isd.com.ua) See also ordermodel.js
 *
 */
public class OrderLineParameterDTO extends DTO {

	private static final long serialVersionUID = 6919876053194796391L;

	private Long orderLineParameterId;
	private Long productParameterId;
	private String value;
	private Long parameterUOM;
	private Long orderLineId;
	private String derivative;

	public Long getOrderLineParameterId() {
		return orderLineParameterId;
	}

	public void setOrderLineParameterId(Long orderLineParameterId) {
		this.orderLineParameterId = orderLineParameterId;
	}

	public Long getProductParameterId() {
		return productParameterId;
	}

	public void setProductParameterId(Long productParameterId) {
		this.productParameterId = productParameterId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDerivative() {
		return derivative;
	}

	public void setDerivative(String derivative) {
		this.derivative = derivative;
	}

	public Long getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(Long orderLineId) {
		this.orderLineId = orderLineId;
	}

	public Long getParameterUOM() {
		return parameterUOM;
	}

	public void setParameterUOM(Long parameterUOM) {
		this.parameterUOM = parameterUOM;
	}

	
}
