package org.proto1.domain.party;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.proto1.domain.AbstractEntity;

@Entity
@Table(name="ORGANIZATION_UNIT")
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
