package org.proto1.domain.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// ToDo : 
// Formatter for name from parameter values
// Values source
// Checking value procedure
@Entity
public class ProductParameterTemplate implements Serializable {
	
	private static final long serialVersionUID = 6098362966389176738L;

	@Id
	@ManyToOne
	@JoinColumn(name="parameter_id")
	private Product product;
	@Id
	@ManyToOne
	@JoinColumn(name="parameter_id")
	private Parameter parameter;
	
	
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

	
}
