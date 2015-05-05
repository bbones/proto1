/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="UNIT_OF_MEASUREMENT")
public class UnitOfMeasurement extends AbstractEntity {
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="unitOfMeasurement", fetch=FetchType.EAGER)
	private List<UnitOfMeasurementName> unitOfMeasurementNames = new ArrayList<UnitOfMeasurementName>();

	public List<UnitOfMeasurementName> getUnitOfMeasurementNames() {
		return unitOfMeasurementNames;
	}

	public void setUnitOfMeasurementNames(
			List<UnitOfMeasurementName> unitOfMeasurementNames) {
		this.unitOfMeasurementNames = unitOfMeasurementNames;
	}
	
	public UnitOfMeasurementName getTranslation(Language language) {
		for (UnitOfMeasurementName name: unitOfMeasurementNames) {
			if (name.getLanguage().equals(language)) {
				return name;
			}
		}
		return null;
	}


}
