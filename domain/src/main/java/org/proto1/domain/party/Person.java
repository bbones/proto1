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
