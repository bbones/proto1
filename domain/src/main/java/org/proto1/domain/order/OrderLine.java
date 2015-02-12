package org.proto1.domain.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.product.Product;

@Entity
public class OrderLine extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="orderLine")
	private List<OrderLineParameter> orderLineParameterList;
	

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

	public List<OrderLineParameter> getOrderLineParameterList() {
		return orderLineParameterList;
	}

	public void setOrderLineParameterList(
			List<OrderLineParameter> orderLineParameterList) {
		this.orderLineParameterList = orderLineParameterList;
	}

	
}
