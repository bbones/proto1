package org.proto1.domain.product;

import javax.persistence.Entity;
import javax.persistence.Id;

// ToDo : 
// Formatter for name from parameter values
// Values source
// Checking value procedure
@Entity
public class ProductParameterTemplate {
	@Id
	private Product product;
	@Id
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
