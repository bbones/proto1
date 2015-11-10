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
 * Request.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 3, 2015
 */
package org.proto1.protofront.order;

import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.order.Request;
import org.proto1.dto.order.RequestDTO;
import org.proto1.services.order.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@RestController
@RequestMapping("/requests")
public class RequestController extends BaseOrderController<RequestService> {
	
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getList(@RequestParam Long languageId) {
		return baseOrderService.getOrderList(languageId);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET )
	public @ResponseBody RequestDTO  get(@PathVariable Long id, @RequestParam Long languageId) {
		return mapper.map(baseOrderService.get(id), RequestDTO.class);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST )
	public @ResponseBody RequestDTO save(@RequestParam Long languageId, RequestDTO requestDTO) {
		Request po = mapper.map(requestDTO, Request.class);
		po = baseOrderService.save(po);
		requestDTO =  mapper.map(po, RequestDTO.class);
		return requestDTO;
	}
	
	@RequestMapping(value = "/{requestId}", method = RequestMethod.DELETE)
	public void deleteRequest(@PathVariable Long requestId) {
		baseOrderService.delete(requestId);
	}

}
