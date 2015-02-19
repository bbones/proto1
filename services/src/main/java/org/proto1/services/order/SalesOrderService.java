package org.proto1.services.order;

import java.util.List;
import java.util.Map;

public interface SalesOrderService {

	List<Map<String, Object>> getSalesOrderList(Long languageId);

	List<Map<String, Object>> getSalesOrderLines(Long soId, Long languageId);

}
