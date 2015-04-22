/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * TODO Inherited parameters
 */
package org.proto1.domain.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.UnitOfMeasurement;

@Entity
public class ReceiptItem extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name="RECEIPT_ID")
	@NotNull
	private Receipt receipt;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	@NotNull
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="UNIT_OF_MEASUREMENT_ID")
	@NotNull
	private UnitOfMeasurement unitOfMeasurement;
	
	private double qnty;
	/*
	 * To ReceiptItem marked master BOM calculator copies derivative parameters
	 */
	private boolean master; 

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

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}


}
