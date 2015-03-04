package org.proto1.protofront;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.proto1.dto.ProductionOrderDTO;
import org.proto1.services.order.DemandService;
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
	
	@RequestMapping(value = "getconsol/{languageId}&{productId}", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody List<Map<String, Object>> getConsolidatedDemand(
			@PathVariable("languageId") Long languageId, 
			@PathVariable("productId") Long productId,
			@RequestBody Long[] paramList) {
		return demandService.getConsolidatedDemand(languageId, productId, paramList);
	}
	
	@RequestMapping(value = "/createProdOrder", method = RequestMethod.POST, consumes="application/json")
	public Long createProdOrder(@RequestBody ProductionOrderDTO productionOrder) {
		return null;
	}

}
