package org.proto1.domain.party;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name="ENTERPRISE_ID")
public class Enterprise extends Party {
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="enterprise")
	private List<EnterpriseName> enterpriseNames;

	public List<EnterpriseName> getEnterpriseNames() {
		return enterpriseNames;
	}

	public void setEnterpriseNames(List<EnterpriseName> enterpriseNames) {
		this.enterpriseNames = enterpriseNames;
	}
	
}
