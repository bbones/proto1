/**
 * Request.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 3, 2015
 */
package org.proto1.protofront.order;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.order.Request;
import org.proto1.dto.order.RequestDTO;
import org.proto1.services.order.RequestService;
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
@RequestMapping("/requests")
public class RequestController extends BaseOrderController<RequestService> {
	
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getList(@RequestParam Long languageId) {
		return baseOrderService.getOrderList(languageId);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST )
	public @ResponseBody Map<String, Object> save(@RequestParam Long languageId,
			RequestDTO requestDTO) 
			throws InstantiationException, IllegalAccessException, ParseException {
		Request po = mapper.map(requestDTO, Request.class);
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
