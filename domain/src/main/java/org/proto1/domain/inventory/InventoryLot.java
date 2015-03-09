package org.proto1.domain.inventory;

import java.util.List;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.product.Product;

public class InventoryLot extends AbstractEntity {
	private Product product;
	private Location location;
	private List<InventoryLotParameter> inventoryLotParameterList;
	private Double qnty;
	private UnitOfMeasurement unitOfMeasurement;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<InventoryLotParameter> getInventoryLotParameterList() {
		return inventoryLotParameterList;
	}

	public void setInventoryLotParameterList(
			List<InventoryLotParameter> inventoryLotParameterList) {
		this.inventoryLotParameterList = inventoryLotParameterList;
	}

	public Double getQnty() {
		return qnty;
	}

	public void setQnty(Double qnty) {
		this.qnty = qnty;
	}

	public UnitOfMeasurement getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}


}
