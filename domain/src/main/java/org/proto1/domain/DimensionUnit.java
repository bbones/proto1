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
@Table(name="DIM_UNIT")
public class DimensionUnit extends AbstractEntity {
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="dimensionUnit")
	private List<DimensionUnitName> dimensionUnitNames;

	public List<DimensionUnitName> getDimensionUnitNames() {
		return dimensionUnitNames;
	}

	public void setDimensionUnitNames(List<DimensionUnitName> dimensionUnitNames) {
		this.dimensionUnitNames = dimensionUnitNames;
	}
	
	
}
