package org.proto1.services.order;

import org.proto1.domain.order.ProductionOrder;

public interface ProductionOrderService extends BaseOrderService<ProductionOrder>{


	/**
	 * @param productionOrderId Production Order Id
	 */
	void createOrderBOMs(Long productionOrderId);

}
