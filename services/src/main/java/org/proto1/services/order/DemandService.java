/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.order;

import java.util.List;
import java.util.Map;

public interface DemandService {

	List<Map<String, Object>> getConsolidatedDemand(Long languageId, Long productId, Long[] paramList);

}
