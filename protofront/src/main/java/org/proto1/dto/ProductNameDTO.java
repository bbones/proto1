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

import org.proto1.domain.Language;

public class ProductNameDTO extends DTO {

	private static final long serialVersionUID = -1360674151528992261L;

	private Long nameId;
	private Long productId;
	private Long languageId;
	private String productName;
	
	public ProductNameDTO() {
		
	}
	
	public ProductNameDTO(Long languageId, String productName) {
		this.languageId = languageId;
		this.productName = productName;
	}


	public ProductNameDTO(Long productId, Language language, String productName) {
		this.setProductId(productId);
		this.languageId = language.getId();
		this.productName = productName;
	}


	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getNameId() {
		return nameId;
	}

	public void setNameId(Long nameId) {
		this.nameId = nameId;
	}

}
