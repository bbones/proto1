/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.repository.order.OrderLineParameterRepository;
import org.proto1.repository.order.OrderLineRepository;
import org.proto1.repository.order.SalesOrderRepository;
import org.proto1.services.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderServiceBean implements SalesOrderService {
	
	@Autowired
	SalesOrderRepository salesOrderRepository;

	@Autowired
	OrderLineRepository salesOrderLineRepository;

	@Autowired
	OrderLineParameterRepository salesOrderLineParameterRepository;

	@Autowired
	ApplicationConstants applicationConstant;


	public List<Map<String, Object>> getSalesOrderList(Long languageId) {
		return salesOrderRepository.getListByLanguageId(languageId, 
				applicationConstant.getDefaultBuyerRole().getId());
	}


	public List<Map<String, Object>> getSalesOrderLines(Long soId,
			Long languageId) {
		return salesOrderLineRepository.getOrderLineList(soId, languageId);
	}


	public List<Map<String, Object>> getSalesOrderLineParameters(Long olId,
			Long languageId) {
		return salesOrderLineParameterRepository.getOrderLineParameters(languageId, olId);
	}

}
