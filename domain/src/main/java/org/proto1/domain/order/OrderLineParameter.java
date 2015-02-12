package org.proto1.domain.order;

import javax.persistence.Entity;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.product.Parameter;

@Entity
public class OrderLineParameter extends AbstractEntity {

	private OrderLine orderLine;
	private Parameter parameter;
	private String parameterValue;

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

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

}
