/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.party;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Language;

@Entity
@Table(name="ENTERPRISE_NAME", uniqueConstraints=@UniqueConstraint(columnNames = {"ENTERPRISE_ID", "LANGUAGE_ID"})) 
public class EnterpriseName extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name="ENTERPRISE_ID")
	private Enterprise enterprise;

	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;


	@NotNull
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
	

}
