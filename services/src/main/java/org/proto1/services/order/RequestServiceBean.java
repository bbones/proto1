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
 * RequestServiceBean.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 3, 2015
 */
package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.Request;
import org.proto1.repository.order.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@Service
public class RequestServiceBean extends BaseOrderServiceBean<Request>  implements RequestService {

	@Autowired
	RequestRepository requestRepository;
	/* (non-Javadoc)
	 * @see org.proto1.services.order.BaseOrderService#getOrderList(java.lang.Long)
	 */
	public List<Map<String, Object>> getOrderList(Long languageId) {
		return requestRepository.getList();
	}
	/* (non-Javadoc)
	 * @see org.proto1.services.order.RequestService#save(org.proto1.domain.order.Request)
	 */
	public Request save(Request request) {
		return requestRepository.save(request);
	}
	
	public void delete(Long orderId) {
		requestRepository.delete(orderId);
	}

	public Request get(Long orderId) {
		return requestRepository.findOne(orderId);
	}
}
