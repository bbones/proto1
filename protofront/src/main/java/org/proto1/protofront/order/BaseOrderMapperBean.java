package org.proto1.protofront.order;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.proto1.domain.Language;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.order.BaseOrder;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.OrderLineParameter;
import org.proto1.domain.product.Product;
import org.proto1.dto.order.OrderDTO;
import org.proto1.dto.order.OrderLineDTO;
import org.proto1.dto.order.OrderLineParameterDTO;
import org.proto1.services.UnitOfMeasurementService;
import org.proto1.services.parameter.ParameterService;
import org.proto1.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseOrderMapperBean implements BaseOrderMapper {

	@Autowired
	ProductService productService;
	
	@Autowired
	UnitOfMeasurementService uomService; 
	/**
	 * From OrderDTO to BaseOrder 
	 * @throws ParseException 
	 */
	@Override
	public <T extends BaseOrder, S extends OrderDTO> T map(S source, Class<T> destinationClass) 
				throws InstantiationException, IllegalAccessException, ParseException {
		T result = destinationClass.newInstance();
		result.setId(source.getOrderId());
		result.setDocumentNo(source.getOrderNo());
		result.setIssueDate(source.getIssueDate());
		result.setVersion(source.getVersion());
		result.setLines(new ArrayList<OrderLine>());
		if (source.getOrderLines() != null) {
			mapOrderLines(source.getOrderLines(), result.getLines(), result);
		}
		return result;
	}

	@Override
	public void mapOrderLines(List<OrderLineDTO> orderLines, List<OrderLine> lines, BaseOrder order) {
		for(OrderLineDTO old : orderLines) {
			OrderLine ol = new OrderLine();
			mapOrderLine(old, ol, order);
			lines.add(ol);
		}
	}

	@Override
	public void mapOrderLine(OrderLineDTO old, OrderLine ol,  BaseOrder order) {
		
		ol.setOrder(order);
		Product product = productService.getById(old.getProductId());
		ol.setProduct(product);
		ol.setId(old.getOrderLineId());
		ol.setQnty(old.getQnty());
		UnitOfMeasurement uom = uomService.get(old.getUomId());
		ol.setUnitOfMeasurement(uom);
		ol.setOrderLineParameterList(new ArrayList<OrderLineParameter>());
		if (old.getParameterList() != null) {
			mapOrderLineParameters(old.getParameterList(), ol.getOrderLineParameterList(), ol);
		}
		ol.setVersion(old.getVersion());
	}

	@Override
	public void mapOrderLineParameters(
			List<OrderLineParameterDTO> parameterList,
			List<OrderLineParameter> orderLineParameterList, OrderLine orderLine) {
		for(OrderLineParameterDTO olpd : parameterList) {
			OrderLineParameter olp = new OrderLineParameter();
			mapOrderLineParameter(olpd, olp, orderLine);
			orderLineParameterList.add(olp);
		}
	}

	@Override
	public void mapOrderLineParameter(OrderLineParameterDTO olpd,
			OrderLineParameter olp, OrderLine orderLine) {
		
		olp.setProductParameter(productService.getProductParameter(olpd.getPpId()));
		olp.setValue(olpd.getpValue());
		olp.setOrderLine(orderLine);
		olp.setVersion(olp.getVersion());
		olp.setDerivative(olp.getProductParameter().isDerivative());
		olp.setUnitOfMeasurement(uomService.get(olpd.getpUOM()));
	}

	/**
	 * From BaseOrder to DTO
	 */
	@Override
	public <T extends OrderDTO, S extends BaseOrder> T mapToDTO(S source, Class<T> destinationClass, 
				Language language) 
				throws InstantiationException, IllegalAccessException {
		T result = destinationClass.newInstance();
		result.setOrderId(source.getId());
		result.setOrderNo(source.getDocumentNo());
		result.setIssueDate(source.getIssueDate());
		result.setVersion(source.getVersion());
		result.setOrderLines(new ArrayList<OrderLineDTO>());
		mapOrderLines(source.getLines(), result.getOrderLines(), language);
		return result;
	}

	@Override
	public void mapOrderLines(List<OrderLine> orderLines, List<OrderLineDTO> linesDTO, Language language) {
		for(OrderLine ol : orderLines) {
			OrderLineDTO old = new OrderLineDTO();
			mapOrderLine(ol, old, language);
			linesDTO.add(old);
		}
	}

	@Override
	public void mapOrderLine(OrderLine ol, OrderLineDTO old, Language language) {
		
		old.setOrderLineId(ol.getOrder().getId());
		old.setProductId(ol.getProduct().getId());
		old.setOrderLineId(ol.getId());
		old.setQnty(old.getQnty());
		old.setUomId(ol.getUnitOfMeasurement().getId());
		old.setParameterList(new ArrayList<OrderLineParameterDTO>());
		mapOrderLineParameters(old.getParameterList(), ol.getOrderLineParameterList(), ol);
		old.setVersion(old.getVersion());
	}

	@Override
	public void mapOrderLineParameters(
			List<OrderLineParameter> parameterList,
			List<OrderLineParameterDTO> parameterListDTO, Language language) {
		for(OrderLineParameter olp : parameterList) {
			OrderLineParameterDTO olpd = new OrderLineParameterDTO();
			mapOrderLineParameter(olp, olpd, language);
			parameterListDTO.add(olpd);
		}
	}

	@Override
	public void mapOrderLineParameter(OrderLineParameter olp,
			OrderLineParameterDTO olpd, Language language) {
		
		olpd.setPpId(olp.getProductParameter().getId());
		olpd.setpValue(olp.getValue());
		olpd.setOlId(olp.getOrderLine().getId());
		olpd.setVersion(olp.getVersion());
		olpd.setDerivative(olp.getProductParameter().isDerivative() ? "true" : "false");
		olpd.setpUOM(olp.getUnitOfMeasurement().getId());
	}

}
