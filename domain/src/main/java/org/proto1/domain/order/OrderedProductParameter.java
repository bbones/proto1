package org.proto1.domain.order;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.proto1.domain.product.Parameter;

@Entity
public class OrderedProductParameter {
	@Id
	private OrderLine orderLine;
	
	@Id
	private Parameter parameter;
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

	
}
