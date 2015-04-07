/**
 * Request.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 3, 2015
 */
package org.proto1.protofront.order;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.proto1.domain.Language;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.Request;
import org.proto1.domain.product.Product;
import org.proto1.dto.order.OrderLineDTO;
import org.proto1.dto.order.RequestDTO;
import org.proto1.protofront.BaseController;
import org.proto1.services.LanguageService;
import org.proto1.services.UnitOfMeasurementService;
import org.proto1.services.order.RequestService;
import org.proto1.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/lang:{languageId}", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getList(@PathVariable Long languageId) {
		return requestService.getOrderList(languageId);
	}

	@RequestMapping(value = "/lang:{languageId}", method = RequestMethod.POST )
	public @ResponseBody Map<String, Object> save(@PathVariable Long languageId,
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


	@RequestMapping(value = "/{orderId}/lines/lang:{languageId}", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getOrderLines(@PathVariable Long orderId, 
			@PathVariable Long languageId) {
		return requestService.getOrderLines(orderId, languageId);
	}

	@RequestMapping(value = "/lines/lang:{languageId}", method = RequestMethod.POST )
	public @ResponseBody OrderLineDTO  saveOrderLine(@PathVariable Long languageId,
			OrderLineDTO orderLineDTO) {
		OrderLine ol = new OrderLine();
		if (orderLineDTO.getOrderLineId() != null)
			ol.setId(orderLineDTO.getOrderLineId());
		ol.setOrder(requestService.get(orderLineDTO.getOrderId()));
		ol.setQnty(orderLineDTO.getQnty());
		UnitOfMeasurement uom = unitOfMeasurementService.get(orderLineDTO.getUomId());
		ol.setUnitOfMeasurement(uom);
		ol.setPrice(orderLineDTO.getPrice());
		ol.setAmount(orderLineDTO.getAmount());
		Product product = productService.getById(orderLineDTO.getProductId());
		ol.setProduct(product);
		ol.setVersion(orderLineDTO.getVersion());
		ol = requestService.saveOrderLine(ol);
		orderLineDTO.setOrderLineId(ol.getId());
		Language language = languageService.get(languageId);
		orderLineDTO.setProductName(ol.getProduct().getTranslation(language).getName());
		orderLineDTO.setUomName(uom.getTranslation(language).getShortName());
		return orderLineDTO;
	}

	@RequestMapping(value = "/lines/{id}", method = RequestMethod.DELETE )
	public void deleteOrderLine(@PathVariable Long id) {
		requestService.deleteOrderLine(id);
	}
}
