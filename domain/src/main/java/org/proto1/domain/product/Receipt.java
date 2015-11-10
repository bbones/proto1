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
