/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
package org.proto1.domain.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.product.Product;

@Entity
public class OrderLine extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="ORDER_ID")
	private BaseOrder order;

	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="UNIT_OF_MEASUREMENT_ID")
	private UnitOfMeasurement unitOfMeasurement;
	
	private Double qnty;
	
	private Double price;
	
	private Double amount;
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="orderLine")
	private List<OrderLineParameter> orderLineParameterList;
	

	public BaseOrder getOrder() {
		return order;
	}

	public void setOrder(BaseOrder order) {
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

	public Double getQnty() {
		return qnty;
	}

	public void setQnty(Double qnty) {
		this.qnty = qnty;
	}

	public UnitOfMeasurement getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	/**
	 * @return
	 */
	public List<OrderLineParameter> getDerivativeParameters() {
		List<OrderLineParameter> olpList = new ArrayList<OrderLineParameter>();
		for(OrderLineParameter olp : getOrderLineParameterList()) {
			if (olp.isDerivative()) {
				olpList.add(olp);
			}
		}
		return olpList;
	}
	
}
