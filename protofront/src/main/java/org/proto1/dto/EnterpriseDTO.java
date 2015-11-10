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

	@DTODecode(destination="setId")
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

	@DTODecode(destination="setEskId")
	public Long getEskId() {
		return eskId;
	}

	public void setEskId(Long eskId) {
		this.eskId = eskId;
	}

	
}
