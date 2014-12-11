package org.proto1.domain.order;

import java.util.Map;

import org.proto1.domain.Identified;
import org.proto1.domain.Parameter;
import org.proto1.domain.product.Product;

public class OrderLine implements Identified {
	private Long id;
	private Order order;
	private Product product;
	private Map<Parameter, Object> productParameterMap;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Map<Parameter, Object> getProductParameterMap() {
		return productParameterMap;
	}
	public void setProductParameterMap(Map<Parameter, Object> productParameterMap) {
		this.productParameterMap = productParameterMap;
	}
	
	
}
