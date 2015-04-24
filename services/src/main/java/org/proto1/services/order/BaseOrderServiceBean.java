package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.proto1.domain.order.BaseOrder;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.OrderLineParameter;
import org.proto1.repository.order.OrderLineParameterRepository;
import org.proto1.repository.order.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseOrderServiceBean<T extends BaseOrder> implements BaseOrderService<T> {
	
	@Autowired
	OrderLineRepository orderLineRepository;

	@Autowired
	OrderLineParameterRepository orderLineParameterRepository;


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
}
