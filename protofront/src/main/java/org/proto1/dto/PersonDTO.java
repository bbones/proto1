package org.proto1.dto;

import javax.validation.constraints.NotNull;

public class PersonDTO extends DTO {

	private static final long serialVersionUID = 3451364193014660604L;

	private Long id;
	@NotNull
	private String firstName;
	private String middleName;
	@NotNull
	private String lastName;
	
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}


}
