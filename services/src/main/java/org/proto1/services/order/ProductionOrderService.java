package org.proto1.services.order;

import java.util.List;
import java.util.Map;

public interface ProductionOrderService {

	List<Map<String, Object>> getProductionOrderList(Long languageId);

	List<Map<String, Object>> getOrderLines(Long soId, Long languageId);

	List<Map<String, Object>> getOrderLineParameters(Long olId,
			Long languageId);

}