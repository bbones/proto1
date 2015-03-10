/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.order;

import java.util.List;
import java.util.Map;

public interface SalesOrderService {

	List<Map<String, Object>> getSalesOrderList(Long languageId);

	List<Map<String, Object>> getOrderLines(Long soId, Long languageId);

	List<Map<String, Object>> getOrderLineParameters(Long olId, Long languageId);

}
