/**
 * TODO Abstract @RestController for lines and parameters
 */
package org.proto1.protofront.order;

import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.dto.order.ProductionOrderDTO;
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
public class ProductionOrderController extends BaseOrderController<ProductionOrderService> {
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  prodOrderListByLanguage(@RequestParam Long languageId) {
		return baseOrderService.getOrderList(languageId);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET )
	public @ResponseBody ProductionOrderDTO get(@PathVariable Long id, @RequestParam Long languageId) {
		return mapper.map(baseOrderService.get(id), ProductionOrderDTO.class);
	}
	
	@RequestMapping(value = "{poId}/createOrderBOMs", method = RequestMethod.POST )
	public void  createOrderBOMs(@PathVariable Long poId) {
		baseOrderService.createOrderBOMs(poId);
	}

}
