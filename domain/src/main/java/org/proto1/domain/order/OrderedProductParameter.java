package org.proto1.domain.order;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.proto1.domain.product.Parameter;

@Entity
public class OrderedProductParameter implements Serializable {

	private static final long serialVersionUID = 5876611867705060840L;

	@Id
	@ManyToOne
	@JoinColumn(name="order_line_id")
	private OrderLine orderLine;
	
	@Id
	@ManyToOne
	@JoinColumn(name="parameter_id")
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
