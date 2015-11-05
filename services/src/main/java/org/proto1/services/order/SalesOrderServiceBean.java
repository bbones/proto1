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

import org.proto1.domain.order.SalesOrder;
import org.proto1.repository.order.SalesOrderRepository;
import org.proto1.services.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderServiceBean extends BaseOrderServiceBean<SalesOrder> implements SalesOrderService {
	
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

	/* (non-Javadoc)
	 * @see org.proto1.services.order.BaseOrderService#get(java.lang.Long)
	 */
	public SalesOrder get(Long orderId) {
		return salesOrderRepository.findOne(orderId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.order.BaseOrderService#save(org.proto1.domain.order.BaseOrder)
	 */
	public SalesOrder save(SalesOrder order) {
		return salesOrderRepository.save(order);
	}

}
