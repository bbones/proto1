/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package org.proto1.domain.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.UnitOfMeasurement;

//	TODO : 
//		Formatter for name from parameter values
//		Values source
//		Checking value procedure

@Entity
@Table(name="PRODUCT_PARAMETER")
public class ProductParameter extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="PARAMETER_ID")
	private Parameter parameter;
	
	@ManyToOne
	@JoinColumn(name="DEFAULT_UOM_ID")
	private UnitOfMeasurement defaultUOM;
	
	private boolean required;
	
	private boolean derivative;
	
	private String valueProvider;
	
	@ManyToOne
	@JoinColumn(name="MASTER_PARAMETER_ID")
	private ProductParameter masterParameter;

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

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isDerivative() {
		return derivative;
	}

	public void setDerivative(boolean derivative) {
		this.derivative = derivative;
	}

	public String getValueProvider() {
		return valueProvider;
	}

	public void setValueProvider(String valueProvider) {
		this.valueProvider = valueProvider;
	}

	public ProductParameter getMasterParameter() {
		return masterParameter;
	}

	public void setMasterParameter(ProductParameter masterParameter) {
		this.masterParameter = masterParameter;
	}

	public UnitOfMeasurement getDefaultUOM() {
		return defaultUOM;
	}

	public void setDefaultUOM(UnitOfMeasurement defaultUOM) {
		this.defaultUOM = defaultUOM;
	}

	
}
