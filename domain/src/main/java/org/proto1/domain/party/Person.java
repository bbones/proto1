/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.party;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="PERSON_ID")
public class Person extends Party {
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="person")
	private List<PersonName> personNames;
	
	private String personIdCode;
	
	private String passportNo;

	public String getPersonIdCode() {
		return personIdCode;
	}

	public void setPersonIdCode(String personIdCode) {
		this.personIdCode = personIdCode;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String pasportNo) {
		this.passportNo = pasportNo;
	}

	public List<PersonName> getPersonNames() {
		return personNames;
	}

	public void setPersonNames(List<PersonName> personNames) {
		this.personNames = personNames;
	}
	
}
