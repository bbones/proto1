/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
package org.proto1.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="UNIT_OF_MEASUREMENT")
public class UnitOfMeasurement extends AbstractEntity {
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="unitOfMeasurement")
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
