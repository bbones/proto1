package org.proto1.protofront.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.services.order.ProductionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prodorder")
public class ProductionOrderController {
	@Autowired
	Mapper mapper;
	
	@Autowired
	ProductionOrderService productionOrderService;
	
	@RequestMapping(value = "listbylang/{languageId}", method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>>  prodOrderListByLanguage(@PathVariable Long languageId) {
		return productionOrderService.getProductionOrderList(languageId);
	}
	

	@RequestMapping(value = "lines/{poId}&{languageId}", method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>>  prodOrderLineList(@PathVariable Long poId, @PathVariable Long languageId) {
		return productionOrderService.getOrderLines(poId, languageId);
	}
	
	@RequestMapping(value = "lineparameters/{olId}&{languageId}", method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>>  prodOrderLineParameters(@PathVariable Long olId, @PathVariable Long languageId) {
		List<Map<String, Object>> result = productionOrderService.getOrderLineParameters(olId, languageId);
		return (result == null) ? new ArrayList<Map<String, Object>>() : result;
	}
}
