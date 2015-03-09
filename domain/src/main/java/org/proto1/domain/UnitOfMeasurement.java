/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="UNIT_OF_MEASUREMENT")
public class UnitOfMeasurement extends AbstractEntity {
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="unitOfMeasurement")
	private List<UnitOfMeasurementName> unitOfMeasurementNames;

	public List<UnitOfMeasurementName> getUnitOfMeasurementNames() {
		return unitOfMeasurementNames;
	}

	public void setUnitOfMeasurementNames(
			List<UnitOfMeasurementName> unitOfMeasurementNames) {
		this.unitOfMeasurementNames = unitOfMeasurementNames;
	}

}
