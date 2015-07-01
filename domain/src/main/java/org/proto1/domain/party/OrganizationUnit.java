package org.proto1.domain.party;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.proto1.domain.AbstractEntity;

@Entity
@Table(name="ORGANIZATION_UNIT")
public class OrganizationUnit extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name="ENTERPRISE_ID")
	private Enterprise enterprise;
	
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="organizationUnit")
	private List<OrganizationUnitName> names;

	public List<OrganizationUnitName> getNames() {
		return names;
	}

	public void setNames(List<OrganizationUnitName> names) {
		this.names = names;
	}
	
	
}
