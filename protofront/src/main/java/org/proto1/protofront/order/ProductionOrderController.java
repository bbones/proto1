/**
 * TODO Abstract @RestController for lines and parameters
 */
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prodorders")
public class ProductionOrderController {
	@Autowired
	Mapper mapper;
	
	@Autowired
	ProductionOrderService productionOrderService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  prodOrderListByLanguage(@RequestParam Long languageId) {
		return productionOrderService.getOrderList(languageId);
	}
	

	@RequestMapping(value = "{poId}/lines", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  prodOrderLineList(@PathVariable Long poId, @RequestParam Long languageId) {
		return productionOrderService.getOrderLines(poId, languageId);
	}
	
	@RequestMapping(value = "lines/{olId}/lineparameters", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  prodOrderLineParameters(@PathVariable Long olId, @RequestParam Long languageId) {
		List<Map<String, Object>> result = productionOrderService.getOrderLineParameters(olId, languageId);
		return (result == null) ? new ArrayList<Map<String, Object>>() : result;
	}

	@RequestMapping(value = "{poId}/createOrderBOMs", method = RequestMethod.POST )
	public void  createOrderBOMs(@PathVariable Long poId) {
		productionOrderService.createOrderBOMs(poId);
	}

}
