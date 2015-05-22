/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.party;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.proto1.domain.Language;

@Entity
@PrimaryKeyJoinColumn(name="PERSON_ID")
public class Person extends Party {
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST, mappedBy="person")
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

	@Override
	public String getName(Language language) {
		for (PersonName name : personNames) {
			if(name.getLanguage().equals(language))
				return name.getLastName() + ' ' + name.getFirstName() + ' ' + name.getMiddleName() ;
		}
		return null;
	}
	
}
