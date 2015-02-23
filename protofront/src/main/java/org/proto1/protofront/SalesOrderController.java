package org.proto1.protofront;

import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.services.order.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salesorder")
public class SalesOrderController {
	@Autowired
	Mapper mapper;
	
	@Autowired
	SalesOrderService salesOrderService;
	
	
	@RequestMapping(value = "listbylang/{languageId}", method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>>  salesOrderListByLanguage(@PathVariable Long languageId) {
		return salesOrderService.getSalesOrderList(languageId);
	}
	

	@RequestMapping(value = "lines/{soId}&{languageId}", method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>>  salesOrderLineList(@PathVariable Long soId, @PathVariable Long languageId) {
		return salesOrderService.getSalesOrderLines(soId, languageId);
	}
	
	@RequestMapping(value = "lineparameters/{olId}&{languageId}", method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>>  salesOrderLineParameters(@PathVariable Long olId, @PathVariable Long languageId) {
		return salesOrderService.getSalesOrderLineParameters(olId, languageId);
	}
	
}
