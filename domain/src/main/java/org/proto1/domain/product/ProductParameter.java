/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.proto1.domain.AbstractEntity;

//	TODO : 
//		Formatter for name from parameter values
//		Values source
//		Checking value procedure

@Entity
@Table(name="PRODUCT_PARAMETER")
public class ProductParameter extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="parameter_id")
	private Parameter parameter;
	
	private Boolean required;
	
	private Boolean inherited;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Boolean getInherited() {
		return inherited;
	}

	public void setInherited(Boolean inherited) {
		this.inherited = inherited;
	}

}
