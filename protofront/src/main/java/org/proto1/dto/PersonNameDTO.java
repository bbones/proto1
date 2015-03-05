/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.dto;

import javax.validation.constraints.NotNull;

public class PersonNameDTO extends DTO {

	private static final long serialVersionUID = 331558813224156396L;

	private Long personNameId;
	private Long personId;
	private Long languageId;
	private String languageName;
	@NotNull
	private String firstName;
	private String middleName;
	@NotNull
	private String lastName;

	public Long getPersonNameId() {
		return personNameId;
	}

	public void setPersonNameId(Long personNameId) {
		this.personNameId = personNameId;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
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

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

}
