/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.repository.order.SalesOrderRepository;
import org.proto1.services.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderServiceBean extends BaseOrderServiceBean implements SalesOrderService {
	
	@Autowired
	SalesOrderRepository salesOrderRepository;

	@Autowired
	ApplicationConstants applicationConstant;


	/* (non-Javadoc)
	 * @see org.proto1.services.order.BaseOrderService#getOrderList(java.lang.Long)
	 */
	public List<Map<String, Object>> getOrderList(Long languageId) {
		return salesOrderRepository.getListByLanguageId(languageId, 
				applicationConstant.getDefaultBuyerRole().getId());
	}

	public void delete(Long orderId) {
		salesOrderRepository.delete(orderId);
	}

}
