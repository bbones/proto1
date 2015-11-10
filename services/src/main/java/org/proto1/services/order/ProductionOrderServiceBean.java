/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.proto1.domain.order.ProductionOrder;
import org.proto1.repository.order.ProductionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionOrderServiceBean extends BaseOrderServiceBean<ProductionOrder> implements ProductionOrderService  {
	
	@Autowired
	ProductionOrderRepository productionOrderRepository;
	
	@Autowired
	BOMService bomService;

	public List<Map<String, Object>> getOrderList(Long languageId) {
		return productionOrderRepository.getOrderList();
	}

	public ProductionOrder save(ProductionOrder productionOrder) {
		return productionOrderRepository.save(productionOrder);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.order.ProductionOrderService#createOrderBOMs(java.lang.Long)
	 */
	@Transactional
	public void createOrderBOMs(Long productionOrderId) {
		ProductionOrder productionOrder = productionOrderRepository.findOne(productionOrderId);
		bomService.createBOM(productionOrder);
	}

	public void delete(Long orderId) {
		productionOrderRepository.delete(orderId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.order.BaseOrderService#get(java.lang.Long)
	 */
	public ProductionOrder get(Long orderId) {
		return productionOrderRepository.findOne(orderId);
	}

}
