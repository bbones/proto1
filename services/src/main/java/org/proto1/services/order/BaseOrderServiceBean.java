package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.repository.order.OrderLineParameterRepository;
import org.proto1.repository.order.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseOrderServiceBean implements BaseOrderService {

	@Autowired
	OrderLineRepository salesOrderLineRepository;

	@Autowired
	OrderLineParameterRepository salesOrderLineParameterRepository;


	public List<Map<String, Object>> getOrderList(Long languageId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getOrderLines(Long orderId,
			Long languageId) {
		return salesOrderLineRepository.getOrderLineList(orderId, languageId);
	}


	public List<Map<String, Object>> getOrderLineParameters(Long olId,
			Long languageId) {
		return salesOrderLineParameterRepository.getOrderLineParameters(languageId, olId);
	}

}
