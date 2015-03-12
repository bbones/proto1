/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Language;

@Entity
public class ParameterName extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="parameter_id")
	private Parameter parameter;
	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;
	private String name;

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
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
