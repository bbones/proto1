package org.proto1.domain.party;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Language;

@Entity
@Table(name="ORGANIZATION_UNIT_NAME")
public class OrganizationUnitName extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_UNIT_ID")
	private OrganizationUnit organizationUnit;

	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;

	@NotNull
	private String unitName;

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}
