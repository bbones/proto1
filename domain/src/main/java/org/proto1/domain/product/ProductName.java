/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Language;

@Entity
@Table(name="PRODUCT_NAME", uniqueConstraints=@UniqueConstraint(columnNames = {"PRODUCT_ID", "LANGUAGE_ID"})) 
public class ProductName extends AbstractEntity {
	


	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	@NotNull
	private Product product;

	@ManyToOne
	@JoinColumn(name="LANGUAGE_ID")
	@NotNull
	private Language language;
	
	@NotNull
	private String name;
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	public Language getLanguage() {
		return language;
	}
	
	public void setLanguage(Language language) {
		this.language = language;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	
}
