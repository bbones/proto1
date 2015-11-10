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
package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.proto1.domain.order.BaseOrder;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.OrderLineParameter;
import org.proto1.domain.product.ProductParameter;
import org.proto1.repository.order.OrderLineParameterRepository;
import org.proto1.repository.order.OrderLineRepository;
import org.proto1.repository.product.ProductParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseOrderServiceBean<T extends BaseOrder> implements BaseOrderService<T> {
	
	@Autowired
	OrderLineRepository orderLineRepository;

	@Autowired
	OrderLineParameterRepository orderLineParameterRepository;
	
	@Autowired
	ProductParameterRepository productParameterRepository;


	public List<Map<String, Object>> getOrderLines(Long orderId,
			Long languageId) {
		return orderLineRepository.getOrderLineList(orderId, languageId);
	}
	
	public OrderLine getOrderLine(Long orderLineId) {
		return orderLineRepository.findOne(orderLineId);
	}

	@Transactional
	public OrderLine saveOrderLine(OrderLine orderLine) {
		return orderLineRepository.save(orderLine);
	}

	public void deleteOrderLine(Long orderLineId) {
		orderLineRepository.delete(orderLineId);
	}


	public List<Map<String, Object>> getOrderLineParameters(Long olId,
			Long languageId) {
		return orderLineParameterRepository.getOrderLineParameters(languageId, olId);
	}
	
	public OrderLineParameter saveOrderLineParameter(OrderLineParameter orderLineParameter) {
		return orderLineParameterRepository.save(orderLineParameter);
	}
	
	public void deleteOrderLineParameter(Long orderLineParameterId) {
		orderLineParameterRepository.delete(orderLineParameterId);
	}
	
	public void fillParameters(Long orderLineId) {
		OrderLine ol = orderLineRepository.findOne(orderLineId);
		List<ProductParameter> ppl = productParameterRepository.findByProductId(ol.getProduct().getId());
		for (ProductParameter pp : ppl) {
			OrderLineParameter olp = new OrderLineParameter();
			olp.setProductParameter(pp);
			olp.setOrderLine(ol);
			olp.setUnitOfMeasurement(pp.getDefaultUOM());
			olp.setValue("ND");
			orderLineParameterRepository.save(olp);
		}
	}

}
