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

import org.proto1.domain.order.BaseOrder;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.OrderLineParameter;

public interface BaseOrderService<T extends BaseOrder> {
	
	T get(Long orderId);
	
	T save(T order);
	
	List<Map<String, Object>> getOrderList(Long languageId);

	List<Map<String, Object>> getOrderLines(Long soId, Long languageId);

	List<Map<String, Object>> getOrderLineParameters(Long olId, Long languageId);
	
	OrderLine saveOrderLine(OrderLine orderLine);
	
	void deleteOrderLine(Long id);
	
	void delete(Long orderId);

	/**
	 * @param orderLineId
	 * @return
	 */
	OrderLine getOrderLine(Long orderLineId);

	/**
	 * @param olp
	 * @return
	 */
	OrderLineParameter saveOrderLineParameter(OrderLineParameter olp);
	
	void fillParameters(Long orderLineId);
	
	void deleteOrderLineParameter(Long olParameterId);

}
