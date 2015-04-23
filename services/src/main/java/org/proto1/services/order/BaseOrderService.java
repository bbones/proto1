package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.BaseOrder;
import org.proto1.domain.order.OrderLine;

public interface BaseOrderService<T extends BaseOrder> {
	
	T get(Long orderId);
	
	List<Map<String, Object>> getOrderList(Long languageId);

	List<Map<String, Object>> getOrderLines(Long soId, Long languageId);

	List<Map<String, Object>> getOrderLineParameters(Long olId, Long languageId);
	
	OrderLine saveOrderLine(OrderLine orderLine);
	
	void deleteOrderLine(Long id);
	
	void delete(Long orderId);

}
