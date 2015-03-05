/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.dto;

public class ProductDTO extends DTO {
	private static final long serialVersionUID = 1902362765792727670L;
	
	private Long id;
	private String localizedProductName;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getLocalizedProductName() {
		return localizedProductName;
	}

	public void setLocalizedProductName(String localizedProductName) {
		this.localizedProductName = localizedProductName;
	}

}
