package org.proto1.domain.party;

import org.proto1.domain.AbstractEntity;

public class PersonPosition extends AbstractEntity {
	private OrganizationUnit organizationUnit;
	private Person person;
	
	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}
	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

}
