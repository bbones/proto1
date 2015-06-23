package org.proto1.services.order;

import org.proto1.domain.order.OrderLine;
import org.proto1.repository.order.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineServiceBean implements OrderLineService {

	@Autowired
	OrderLineRepository orderLineRepository;

	public OrderLine get(Long orderLineId) {
		return orderLineRepository.findOne(orderLineId);
	}

}
