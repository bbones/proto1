/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.product.ProductParameter;

@Entity
@Table(name = "ORDER_LINE_PARAMETER", uniqueConstraints = @UniqueConstraint(columnNames = {
		"ORDER_LINE_ID", "PRODUCT_PARAMETER_ID" }))
public class OrderLineParameter extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "ORDER_LINE_ID", nullable=false)
	private OrderLine orderLine;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_PARAMETER_ID", nullable=false)
	private ProductParameter productParameter;
	
	@ManyToOne
	@JoinColumn(name = "UNIT_OF_MEASUREMENT_ID", nullable=false)
	private UnitOfMeasurement unitOfMeasurement;
	
	@Column(nullable = false) 
	private String value;
	
	private boolean derivative = false;

	public OrderLine getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(OrderLine orderLine) {
		this.orderLine = orderLine;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UnitOfMeasurement getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public boolean isDerivative() {
		return derivative;
	}

	public void setDerivative(boolean derivative) {
		this.derivative = derivative;
	}

	public ProductParameter getProductParameter() {
		return productParameter;
	}

	public void setProductParameter(ProductParameter productParameter) {
		this.productParameter = productParameter;
	}

	
}
