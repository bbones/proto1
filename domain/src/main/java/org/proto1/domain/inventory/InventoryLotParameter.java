package org.proto1.domain.inventory;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.product.Parameter;

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
