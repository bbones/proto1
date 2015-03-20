package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.ProductionOrder;

public interface ProductionOrderService {

	List<Map<String, Object>> getProductionOrderList(Long languageId);

	List<Map<String, Object>> getOrderLines(Long soId, Long languageId);

	List<Map<String, Object>> getOrderLineParameters(Long olId,
			Long languageId);
	
	ProductionOrder save(ProductionOrder productionOrder);

	/**
	 * @param productionOrderId Production Order Id
	 */
	void createOrderBOMs(Long productionOrderId);

}
