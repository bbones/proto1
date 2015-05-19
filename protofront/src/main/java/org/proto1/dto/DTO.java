/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.dto;

import java.io.Serializable;

import org.proto1.dtotools.DTODecode;

@SuppressWarnings("serial")
public abstract class DTO implements Serializable{
	
	protected Integer version;

	@DTODecode(destination="version")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
