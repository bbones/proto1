/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.accounting;

import javax.persistence.Entity;

@Entity
public class AnaliticRole {
	private Long Id;
	private String analiticRoleName;
	private String analiticSource;

	public String getAnaliticRoleName() {
		return analiticRoleName;
	}

	public void setAnaliticRoleName(String analiticRoleName) {
		this.analiticRoleName = analiticRoleName;
	}

	public String getAnaliticSource() {
		return analiticSource;
	}

	public void setAnaliticSource(String analiticSource) {
		this.analiticSource = analiticSource;
	}

	@javax.persistence.Id
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

}
