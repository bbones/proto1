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
