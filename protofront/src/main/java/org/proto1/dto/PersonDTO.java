package org.proto1.dto;

import javax.validation.constraints.NotNull;

public class PersonDTO extends DTO {

	private static final long serialVersionUID = 3451364193014660604L;

	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
