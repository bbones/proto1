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
package org.proto1.protofront;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.Mapper;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.OrderLineParameter;
import org.proto1.domain.order.ProductionOrder;
import org.proto1.dto.order.ProductionOrderDTO;
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
	Mapper mapper;

	
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
		for (OrderLine ol : po.getLines()) {
			ol.setOrder(po);
			for (OrderLineParameter olp : ol.getOrderLineParameterList()) {
				olp.setOrderLine(ol);
			}
		}
		po = productioOrderService.save(po);
		return po.getId();
	}

}
