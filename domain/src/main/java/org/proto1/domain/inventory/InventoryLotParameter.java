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
package org.proto1.domain.inventory;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.product.Parameter;

@Entity
public class InventoryLotParameter extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "inventory_lot_id")
	private InventoryLot inventoryLot;
	
	@ManyToOne
	@JoinColumn(name = "parameter_id")
	private Parameter parameter;
	
	@ManyToOne
	@JoinColumn(name = "UNIT_OF_MEASUREMENT_ID")
	private UnitOfMeasurement unitOfMeasurement;
	
	private String value;

	public InventoryLot getInventoryLot() {
		return inventoryLot;
	}

	public void setInventoryLot(InventoryLot inventoryLot) {
		this.inventoryLot = inventoryLot;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UnitOfMeasurement getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}


}
