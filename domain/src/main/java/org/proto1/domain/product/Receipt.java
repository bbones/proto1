/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.Document;

@Entity
public class Receipt extends Document {
	
	@ManyToOne
	private UnitOfMeasurement unitOfMeasurement;
	
	@ManyToOne
	private Product product;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="receipt_ingredients")
	private List<ReceiptItem> ingredients;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="receipt_byproducts")
	private List<ReceiptItem> byProducts;
	
	private boolean byDefault;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ReceiptItem> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<ReceiptItem> ingredients) {
		this.ingredients = ingredients;
	}

	public List<ReceiptItem> getByProducts() {
		return byProducts;
	}

	public void setByProducts(List<ReceiptItem> byProducts) {
		this.byProducts = byProducts;
	}

	public UnitOfMeasurement getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public boolean isByDefault() {
		return byDefault;
	}

	public void setByDefault(boolean byDefault) {
		this.byDefault = byDefault;
	}

	
}
