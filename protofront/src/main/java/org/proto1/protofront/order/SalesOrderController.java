/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront.order;

import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
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
	

	@RequestMapping(value = "{soId}/lines", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  salesOrderLineList(@PathVariable Long soId, @RequestParam Long languageId) {
		return salesOrderService.getOrderLines(soId, languageId);
	}
	
	@RequestMapping(value = "lines/{olId}/lineparameters", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  salesOrderLineParameters(@PathVariable Long olId, @RequestParam Long languageId) {
		return salesOrderService.getOrderLineParameters(olId, languageId);
	}
	
}
