package org.proto1.domain.order;

import javax.persistence.Entity;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.product.Product;

@Entity
public class OrderLine extends AbstractEntity {

	private Long id;
	private Order order;
	private Product product;

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

}
