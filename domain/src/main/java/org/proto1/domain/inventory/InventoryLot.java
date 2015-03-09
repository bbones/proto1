package org.proto1.domain.inventory;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.product.Product;

@Entity
@Table(name = "INVENTORY_LOT")
public class InventoryLot extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;

	@ManyToOne
	@JoinColumn(name="LOCATION_ID")
	private Location location;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="inventoryLot")
	private List<InventoryLotParameter> inventoryLotParameterList;
	
	private Double qnty;
	
	@ManyToOne
	@JoinColumn(name="UNIT_OF_MEASUREMENT_ID")
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
