package org.proto1.services.order;

import org.proto1.domain.order.OrderLine;

public interface OrderLineService {
	
	OrderLine get(Long orderLineId);


}
