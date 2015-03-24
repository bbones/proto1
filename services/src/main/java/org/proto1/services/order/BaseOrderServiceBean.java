package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.repository.order.OrderLineParameterRepository;
import org.proto1.repository.order.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseOrderServiceBean implements BaseOrderService {

	@Autowired
	OrderLineRepository orderLineRepository;

	@Autowired
	OrderLineParameterRepository orderLineParameterRepository;


	public List<Map<String, Object>> getOrderList(Long languageId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getOrderLines(Long orderId,
			Long languageId) {
		return orderLineRepository.getOrderLineList(orderId, languageId);
	}


	public List<Map<String, Object>> getOrderLineParameters(Long olId,
			Long languageId) {
		return orderLineParameterRepository.getOrderLineParameters(languageId, olId);
	}

}
