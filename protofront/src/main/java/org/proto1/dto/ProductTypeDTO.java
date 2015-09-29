/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.dto;


public class ProductTypeDTO extends DTO {
	private static final long serialVersionUID = -1910776023192337434L;

	private Long id;
	private Long parentId;
	private String localizedProductName;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getLocalizedProductName() {
		return localizedProductName;
	}

	public void setLocalizedProductName(String localizedProductName) {
		this.localizedProductName = localizedProductName;
	}

}
