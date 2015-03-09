/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.UnitOfMeasurement;

@Entity
public class ReceiptItem extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name="RECEIPT_ID")
	private Receipt receipt;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="UNIT_OF_MEASUREMENT_ID")
	private UnitOfMeasurement unitOfMeasurement;
	
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

	public double getQnty() {
		return qnty;
	}

	public void setQnty(double qnty) {
		this.qnty = qnty;
	}

	public UnitOfMeasurement getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	
	
}
