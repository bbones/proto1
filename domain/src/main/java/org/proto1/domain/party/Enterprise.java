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
@PrimaryKeyJoinColumn(name="ENTERPRISE_ID")
public class Enterprise extends Party {
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="enterprise")
	private List<EnterpriseName> enterpriseNames;
	
	private Long eskId;

	public List<EnterpriseName> getEnterpriseNames() {
		return enterpriseNames;
	}

	public void setEnterpriseNames(List<EnterpriseName> enterpriseNames) {
		this.enterpriseNames = enterpriseNames;
	}

	public Long getEskId() {
		return eskId;
	}

	public void setEskId(Long eskId) {
		this.eskId = eskId;
	}
	
}
