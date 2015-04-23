/**
 * BaseOrderController.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 23, 2015
 */
package org.proto1.protofront.order;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.proto1.domain.Language;
import org.proto1.domain.order.BaseOrder;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.OrderLineParameter;
import org.proto1.domain.order.Request;
import org.proto1.dto.order.OrderLineDTO;
import org.proto1.dto.order.OrderLineParameterDTO;
import org.proto1.services.LanguageService;
import org.proto1.services.UnitOfMeasurementService;
import org.proto1.services.order.BaseOrderService;
import org.proto1.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@Service
public class BaseOrderController<T extends BaseOrder> {
	@Autowired
	BaseOrderService<T> baseOrderService;
	
	@Autowired
	BaseOrderMapper mapper;
	
	@Autowired
	LanguageService languageService;
	
	@Autowired
	UnitOfMeasurementService unitOfMeasurementService;

	@Autowired
	ProductService productService;

	// Request lines
	@RequestMapping(value = "/{orderId}/lines", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getOrderLines(@PathVariable Long orderId, 
			@RequestParam Long languageId) {
		return baseOrderService.getOrderLines(orderId, languageId);
	}

	@RequestMapping(value = "/lines", method = RequestMethod.POST )
	@Transactional
	public @ResponseBody OrderLineDTO  saveOrderLine(@RequestParam Long languageId,
			OrderLineDTO orderLineDTO) {
		OrderLine ol = new OrderLine();
		BaseOrder order = baseOrderService.get(orderLineDTO.getOrderId());
		mapper.mapOrderLine(orderLineDTO, ol, order);
		ol = baseOrderService.saveOrderLine(ol);
		orderLineDTO.setOrderLineId(ol.getId());
		Language language = languageService.get(languageId);
		orderLineDTO.setProductName(ol.getProduct().getTranslation(language).getName());
		orderLineDTO.setUomName(ol.getUnitOfMeasurement().getTranslation(language).getShortName());
		orderLineDTO.setVersion(ol.getVersion());
		return orderLineDTO;
	}

	@RequestMapping(value = "/lines/{id}", method = RequestMethod.DELETE )
	public void deleteOrderLine(@PathVariable Long id) {
		baseOrderService.deleteOrderLine(id);
	}

	// Request Line Parameters
	@RequestMapping(value = "/lines/{lineId}/lineparameters", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getOrderLineParameters(@PathVariable Long lineId, 
			@RequestParam Long languageId) {
		return baseOrderService.getOrderLines(lineId, languageId);
	}

	@RequestMapping(value = "/lines/lineparameters", method = RequestMethod.POST )
	public @ResponseBody OrderLineParameterDTO  saveOrderLineParameter(@RequestParam Long languageId,
			OrderLineParameterDTO orderLineParameterDTO) {
		OrderLineParameter olp = new OrderLineParameter();
		OrderLine ol = baseOrderService.getOrderLine(orderLineParameterDTO.getOrderLineId());
		mapper.mapOrderLineParameter(orderLineParameterDTO, olp, ol);
		olp = baseOrderService.saveOrderLineParameter(olp);
		orderLineParameterDTO.setOrderLineParameterId(olp.getId());
		orderLineParameterDTO.setVersion(ol.getVersion());
		return orderLineParameterDTO;
	}

	@RequestMapping(value = "/lines/parameters/{id}", method = RequestMethod.DELETE )
	public void deleteOrderLineParameter(@PathVariable Long id) {
		baseOrderService.deleteOrderLine(id);
	}

}
