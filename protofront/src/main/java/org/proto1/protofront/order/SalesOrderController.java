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
package org.proto1.protofront.order;

import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.dto.order.SalesOrderDTO;
import org.proto1.services.order.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salesorders")
public class SalesOrderController {
	@Autowired
	Mapper mapper;
	
	@Autowired
	SalesOrderService salesOrderService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  salesOrderListByLanguage(@RequestParam Long languageId) {
		return salesOrderService.getOrderList(languageId);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET )
	public @ResponseBody SalesOrderDTO  salesOrderListByLanguage(@PathVariable Long id, @RequestParam Long languageId) {
		return mapper.map(salesOrderService.get(id), SalesOrderDTO.class);
	}
	

	@RequestMapping(value = "{soId}/lines", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  salesOrderLineList(@PathVariable Long soId, @RequestParam Long languageId) {
		return salesOrderService.getOrderLines(soId, languageId);
	}
	
	@RequestMapping(value = "lines/{olId}/lineparameters", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  salesOrderLineParameters(@PathVariable Long olId, @RequestParam Long languageId) {
		return salesOrderService.getOrderLineParameters(olId, languageId);
	}
	
}
