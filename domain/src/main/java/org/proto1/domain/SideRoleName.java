/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="SIDE_ROLE_NAME", uniqueConstraints=@UniqueConstraint(columnNames = {"SIDE_ROLE_ID", "LANGUAGE_ID"})) 
public class SideRoleName extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name="SIDE_ROLE_ID")
	private SideRole sideRole;

	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;

	private String name;

	public SideRole getSideRole() {
		return sideRole;
	}

	public void setSideRole(SideRole sideRole) {
		this.sideRole = sideRole;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
