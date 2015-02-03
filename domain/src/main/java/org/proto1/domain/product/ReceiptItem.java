package org.proto1.domain.product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.DimensionUnit;

@Entity
public class ReceiptItem extends AbstractEntity {
	@ManyToOne
	private Receipt receipt;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private DimensionUnit dimensionUnit;
	
	private double qnty;

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public DimensionUnit getDimensionUnit() {
		return dimensionUnit;
	}

	public void setDimensionUnit(DimensionUnit dimensionUnit) {
		this.dimensionUnit = dimensionUnit;
	}

	public double getQnty() {
		return qnty;
	}

	public void setQnty(double qnty) {
		this.qnty = qnty;
	}
	
	
}
