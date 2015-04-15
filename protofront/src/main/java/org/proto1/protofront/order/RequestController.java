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

import javax.transaction.Transactional;

import org.proto1.domain.Language;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.OrderLineParameter;
import org.proto1.domain.order.Request;
import org.proto1.dto.order.OrderLineDTO;
import org.proto1.dto.order.OrderLineParameterDTO;
import org.proto1.dto.order.RequestDTO;
import org.proto1.protofront.BaseController;
import org.proto1.services.LanguageService;
import org.proto1.services.UnitOfMeasurementService;
import org.proto1.services.order.RequestService;
import org.proto1.services.product.ProductService;
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
public class RequestController extends BaseController {
	@Autowired
	RequestService requestService;
	
	@Autowired
	LanguageService languageService;
	
	@Autowired
	UnitOfMeasurementService unitOfMeasurementService;

	@Autowired
	BaseOrderMapper mapper;
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getList(@RequestParam Long languageId) {
		return requestService.getOrderList(languageId);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST )
	public @ResponseBody Map<String, Object> save(@RequestParam Long languageId,
			RequestDTO requestDTO) 
			throws InstantiationException, IllegalAccessException, ParseException {
		Request po = mapper.map(requestDTO, Request.class);
		po = requestService.save(po);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("orderId", po.getId());
		result.put("orderNo", po.getDocumentNo());
		result.put("issueDate", po.getIssueDate());
		return result;
	}
	
	@RequestMapping(value = "/{requestId}", method = RequestMethod.DELETE)
	public void deleteRequest(@PathVariable Long requestId) {
		requestService.delete(requestId);
	}

	// Request lines
	@RequestMapping(value = "/{orderId}/lines", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getOrderLines(@PathVariable Long orderId, 
			@RequestParam Long languageId) {
		return requestService.getOrderLines(orderId, languageId);
	}

	@RequestMapping(value = "/lines", method = RequestMethod.POST )
	@Transactional
	public @ResponseBody OrderLineDTO  saveOrderLine(@RequestParam Long languageId,
			OrderLineDTO orderLineDTO) {
		OrderLine ol = new OrderLine();
		Request request = requestService.get(orderLineDTO.getOrderId());
		mapper.mapOrderLine(orderLineDTO, ol, request);
		ol = requestService.saveOrderLine(ol);
		orderLineDTO.setOrderLineId(ol.getId());
		Language language = languageService.get(languageId);
		orderLineDTO.setProductName(ol.getProduct().getTranslation(language).getName());
		orderLineDTO.setUomName(ol.getUnitOfMeasurement().getTranslation(language).getShortName());
		orderLineDTO.setVersion(ol.getVersion());
		return orderLineDTO;
	}

	@RequestMapping(value = "/lines/{id}", method = RequestMethod.DELETE )
	public void deleteOrderLine(@PathVariable Long id) {
		requestService.deleteOrderLine(id);
	}

	// Request Line Parameters
	@RequestMapping(value = "/lines/{lineId}/lineparameters", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getOrderLineParameters(@PathVariable Long lineId, 
			@RequestParam Long languageId) {
		return requestService.getOrderLines(lineId, languageId);
	}

	@RequestMapping(value = "/lines/lineparameters", method = RequestMethod.POST )
	public @ResponseBody OrderLineParameterDTO  saveOrderLineParameter(@RequestParam Long languageId,
			OrderLineParameterDTO orderLineParameterDTO) {
		OrderLineParameter olp = new OrderLineParameter();
		OrderLine ol = requestService.getOrderLine(orderLineParameterDTO.getOrderLineId());
		mapper.mapOrderLineParameter(orderLineParameterDTO, olp, ol);
		olp = requestService.saveOrderLineParameter(olp);
		orderLineParameterDTO.setOrderLineParameterId(olp.getId());
		orderLineParameterDTO.setVersion(ol.getVersion());
		return orderLineParameterDTO;
	}

	@RequestMapping(value = "/lines/parameters/{id}", method = RequestMethod.DELETE )
	public void deleteOrderLineParameter(@PathVariable Long id) {
		requestService.deleteOrderLine(id);
	}
}
