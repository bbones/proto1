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
package org.proto1.dto;

public class ProductParameterDTO extends DTO {

	private static final long serialVersionUID = -2230010614381916435L;

	private Long productParameterId;
	private Long productId;
	private Long parameterId;
	private String parameterName;
	private Long defaultUOMId;
	private boolean required;

	public Long getProductParameterId() {
		return productParameterId;
	}

	public void setProductParameterId(Long productParameterId) {
		this.productParameterId = productParameterId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getParameterId() {
		return parameterId;
	}

	public void setParameterId(Long parameterId) {
		this.parameterId = parameterId;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Long getDefaultUOMId() {
		return defaultUOMId;
	}

	public void setDefaultUOMId(Long defaultUOMId) {
		this.defaultUOMId = defaultUOMId;
	}

}
