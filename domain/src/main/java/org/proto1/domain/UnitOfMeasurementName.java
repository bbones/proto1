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
@Table(name="UNIT_OF_DIMENSION_NAME", uniqueConstraints=@UniqueConstraint(columnNames = {"UNIT_OF_MEASUREMENT_ID", "LANGUAGE_ID"})) 
public class UnitOfMeasurementName extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="UNIT_OF_MEASUREMENT_ID")
	private UnitOfMeasurement unitOfMeasurement;
	
	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;

	private String shortName;
	private String fullName;

	
	public UnitOfMeasurement getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	
}
