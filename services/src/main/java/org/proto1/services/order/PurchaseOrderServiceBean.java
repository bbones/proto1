/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.PurchaseOrder;
import org.proto1.repository.order.PurchaseOrderRepository;
import org.proto1.services.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderServiceBean extends BaseOrderServiceBean<PurchaseOrder> implements PurchaseOrderService {
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	ApplicationConstants applicationConstant;


	public List<Map<String, Object>> getOrderList(Long languageId) {
		
		return purchaseOrderRepository.getList(languageId, 
				applicationConstant.getDefaultSellerRole().getId());
	}

	public void delete(Long orderId) {
		purchaseOrderRepository.delete(orderId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.order.PurchaseOrderService#save(org.proto1.domain.order.PurchaseOrder)
	 */
	public PurchaseOrder save(PurchaseOrder purchaseOrder) {
		return purchaseOrderRepository.save(purchaseOrder);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.order.BaseOrderService#get(java.lang.Long)
	 */
	public PurchaseOrder get(Long orderId) {
		return purchaseOrderRepository.findOne(orderId);
	}


}
