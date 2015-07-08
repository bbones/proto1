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
	private Long prodParamId;
	private String olpValue;
	private Long uomId;
	private Long olId;
	private String derivative;

	public String getDerivative() {
		return derivative;
	}

	public void setDerivative(String derivative) {
		this.derivative = derivative;
	}

	public Long getOlId() {
		return olId;
	}

	public void setOlId(Long olId) {
		this.olId = olId;
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

	public Long getProdParamId() {
		return prodParamId;
	}

	public void setProdParamId(Long prodParamId) {
		this.prodParamId = prodParamId;
	}

	
}
