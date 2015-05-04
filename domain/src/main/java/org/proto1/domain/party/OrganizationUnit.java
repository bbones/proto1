package org.proto1.domain.party;

import org.proto1.domain.AbstractEntity;

public class OrganizationUnit extends AbstractEntity {
	private OrganizationUnit parentUnit;

	private Enterprise enterprise;
	
	public OrganizationUnit getParentUnit() {
		return parentUnit;
	}

	public void setParentUnit(OrganizationUnit parentUnit) {
		this.parentUnit = parentUnit;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	
}
