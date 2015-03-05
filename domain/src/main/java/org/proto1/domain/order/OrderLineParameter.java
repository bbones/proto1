/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.order;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.DimensionUnit;
import org.proto1.domain.product.Parameter;

@Entity
@Table(name = "ORDER_LINE_PARAMETER", uniqueConstraints = @UniqueConstraint(columnNames = {
		"order_line_id", "parameter_id" }))
public class OrderLineParameter extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "order_line_id")
	private OrderLine orderLine;

	@ManyToOne
	@JoinColumn(name = "parameter_id")
	private Parameter parameter;
	
	@ManyToOne
	@JoinColumn(name = "DIM_INIT_ID")
	private DimensionUnit dimensionUnit;
	
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

	public DimensionUnit getDimensionUnit() {
		return dimensionUnit;
	}

	public void setDimensionUnit(DimensionUnit dimensionUnit) {
		this.dimensionUnit = dimensionUnit;
	}

	
}
