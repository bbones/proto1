/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.dto;

import java.util.List;

import org.proto1.dtotools.DTODecode;

public class EnterpriseDTO extends DTO  {

	private static final long serialVersionUID = -9062428108123912200L;
	
	private Long id;
	private String name;
	private Long eskId;
	
	public EnterpriseDTO() {
		
	}
	
	public EnterpriseDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@DTODecode(destination="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DTODecode(destination="eskId")
	public Long getEskId() {
		return eskId;
	}

	public void setEskId(Long eskId) {
		this.eskId = eskId;
	}

	
}
