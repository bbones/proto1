/**
 * PurchaseOrderController.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 20, 2015
 */
package org.proto1.protofront.order;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.proto1.domain.order.PurchaseOrder;
import org.proto1.services.order.PurchaseOrderService;
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
@RequestMapping("/purchaseorders")
public class PurchaseOrderController extends BaseOrderController <PurchaseOrderService>{
	
	@Autowired
	BaseOrderMapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getList(@RequestParam Long languageId) {
		return baseOrderService.getOrderList(languageId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST )
	public @ResponseBody Map<String, Object> save(@RequestParam Long languageId,
			PurchaseOrderDTO requestDTO) 
			throws InstantiationException, IllegalAccessException, ParseException {
		PurchaseOrder po = mapper.map(requestDTO, PurchaseOrder.class);
		po = baseOrderService.save(po);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("orderId", po.getId());
		result.put("orderNo", po.getDocumentNo());
		result.put("issueDate", po.getIssueDate());
		return result;
	}
	
	@RequestMapping(value = "/{requestId}", method = RequestMethod.DELETE)
	public void deleteRequest(@PathVariable Long requestId) {
		baseOrderService.delete(requestId);
	}



}
