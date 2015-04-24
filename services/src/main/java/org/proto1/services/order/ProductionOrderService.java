package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.ProductionOrder;

public interface ProductionOrderService extends BaseOrderService<ProductionOrder>{


	/**
	 * @param productionOrderId Production Order Id
	 */
	void createOrderBOMs(Long productionOrderId);

}
