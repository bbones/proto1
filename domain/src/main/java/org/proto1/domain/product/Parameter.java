/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Language;
import org.proto1.domain.UnitOfMeasurement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PARAMETER")
public class Parameter extends AbstractEntity {
	public enum Type {
		STRING, NUMBER, DATE
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="parameter")
	@JsonIgnore
	private List<ParameterName> parameterNames = new ArrayList<ParameterName>();
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<UnitOfMeasurement> acceptedUOM = new HashSet<UnitOfMeasurement>();
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<ParameterName> getParameterNames() {
		return parameterNames;
	}

	public void setParameterNames(List<ParameterName> parameterNames) {
		this.parameterNames = parameterNames;
	}

	public Set<UnitOfMeasurement> getAcceptedUOM() {
		return acceptedUOM;
	}

	public void setAcceptedUOM(Set<UnitOfMeasurement> acceptedUOM) {
		this.acceptedUOM = acceptedUOM;
	}
	
	public ParameterName getParameterName(Language language) {
		for (ParameterName pn : parameterNames) {
			if (pn.getLanguage().equals(language)) {
				return pn;
			}
		}
		return null;
	}
}
