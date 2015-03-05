/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class SideRole extends AbstractEntity{
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="sideRole")
	private List<SideRoleName> sideRoleNames;

	public List<SideRoleName> getSideRoleNames() {
		return sideRoleNames;
	}

	public void setSideRoleNames(List<SideRoleName> sideRoleNames) {
		this.sideRoleNames = sideRoleNames;
	}
	
}
