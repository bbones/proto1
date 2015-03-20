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
import org.proto1.domain.product.Parameter;

@Entity
@Table(name = "ORDER_LINE_PARAMETER", uniqueConstraints = @UniqueConstraint(columnNames = {
		"ORDER_LINE_ID", "PARAMETER_ID" }))
public class OrderLineParameter extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "ORDER_LINE_ID", nullable=false)
	private OrderLine orderLine;

	@ManyToOne
	@JoinColumn(name = "PARAMETER_ID", nullable=false)
	private Parameter parameter;
	
	@ManyToOne
	@JoinColumn(name = "UNIT_OF_MEASUREMENT_ID", nullable=false)
	private UnitOfMeasurement unitOfMeasurement;
	
	@Column(nullable = false) 
	private String value;

	public OrderLine getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(OrderLine orderLine) {
		this.orderLine = orderLine;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
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

	
}
