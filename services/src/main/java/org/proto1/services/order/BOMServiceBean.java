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
/**
 * BOMServiceBean.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 23, 2015
 */
package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.proto1.domain.order.BOM;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.ProductionOrder;
import org.proto1.repository.order.BOMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@Service
public class BOMServiceBean extends BaseOrderServiceBean<BOM> implements BOMService {
	
	@Autowired
	ReceiptProvider receiptProvider; 
	
	@Autowired
	BOMRepository bomRepository;
	
	/* (non-Javadoc)
	 * @see org.proto1.services.order.BOMService#createBOM(org.proto1.domain.order.ProductionOrder)
	 */
	
	@Transactional
	public void createBOM(ProductionOrder productionOrder) {
		for(OrderLine ol : productionOrder.getLines()) {
			BOM bom = new BOM();
			bom.setOrderLine(ol);
			bom.setReceipt(receiptProvider.getDefaultReceipt(ol.getProduct()));
			bom.setDocumentNo("BOM:" + productionOrder.getDocumentNo()+"::"+ol.getId());
			bom.calcBOMLines();
			bomRepository.save(bom);
		}

	}

	/* (non-Javadoc)
	 * @see org.proto1.services.order.BOMService#getBOMList(java.lang.Long)
	 */
	public List<Map<String, Object>> getOrderList(Long languageId) {
		return bomRepository.getBOMList();
	}

	public void delete(Long orderId) {
		bomRepository.delete(orderId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.order.BaseOrderService#get(java.lang.Long)
	 */
	public BOM get(Long orderId) {
		return bomRepository.findOne(orderId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.order.BaseOrderService#save(org.proto1.domain.order.BaseOrder)
	 */
	public BOM save(BOM order) {
		return bomRepository.save(order);
	}


}
