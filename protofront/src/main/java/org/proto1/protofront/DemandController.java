/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.proto1.domain.order.ProductionOrder;
import org.proto1.dto.order.ProductionOrderDTO;
import org.proto1.protofront.order.BaseOrderMapper;
import org.proto1.services.order.DemandService;
import org.proto1.services.order.ProductionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demand")
public class DemandController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	DemandService demandService;
	
	@Autowired
	ProductionOrderService productioOrderService;
	
	@Autowired
	BaseOrderMapper mapper;

	
	@RequestMapping(value = "getconsol/{languageId}&{productId}", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody List<Map<String, Object>> getConsolidatedDemand(
			@PathVariable("languageId") Long languageId, 
			@PathVariable("productId") Long productId,
			@RequestBody Long[] paramList) {
		return demandService.getConsolidatedDemand(languageId, productId, paramList);
	}
	
	@RequestMapping(value = "/createProdOrder", method = RequestMethod.POST, consumes="application/json")
	public Long createProdOrder(@RequestBody ProductionOrderDTO productionOrder) 
			throws InstantiationException, IllegalAccessException, ParseException {
		ProductionOrder po = mapper.map(productionOrder, ProductionOrder.class);
		po = productioOrderService.save(po);
		return po.getId();
	}

}
