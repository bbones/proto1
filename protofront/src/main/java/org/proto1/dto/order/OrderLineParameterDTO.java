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

	private Long olpId;
	private Long productParameterId;
	private String olpValue;
	private Long uomId;
	private Long orderLineId;
	private String derivative;

	public Long getProductParameterId() {
		return productParameterId;
	}

	public void setProductParameterId(Long productParameterId) {
		this.productParameterId = productParameterId;
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

	public Long getOlpId() {
		return olpId;
	}

	public void setOlpId(Long olpId) {
		this.olpId = olpId;
	}

	public String getOlpValue() {
		return olpValue;
	}

	public void setOlpValue(String olpValue) {
		this.olpValue = olpValue;
	}

	public Long getUomId() {
		return uomId;
	}

	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}

	
}
