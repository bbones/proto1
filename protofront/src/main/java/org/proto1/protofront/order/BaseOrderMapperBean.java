package org.proto1.protofront.order;

import java.util.ArrayList;
import java.util.List;

import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.order.BaseOrder;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.OrderLineParameter;
import org.proto1.domain.product.Product;
import org.proto1.dto.order.OrderDTO;
import org.proto1.dto.order.OrderLineDTO;
import org.proto1.dto.order.OrderLineParameterDTO;
import org.proto1.services.UnitOfMeasurementService;
import org.proto1.services.product.ParameterService;
import org.proto1.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseOrderMapperBean implements BaseOrderMapper {

	@Autowired
	ProductService productService;
	
	@Autowired
	UnitOfMeasurementService uomService; 
	
	@Autowired
	ParameterService parameterService;
	
	@Override
	public <T extends BaseOrder, S extends OrderDTO> T map(S source, Class<T> destinationClass) 
				throws InstantiationException, IllegalAccessException {
		T result = destinationClass.newInstance();
		result.setId(source.getOrderId());
		result.setDocumentNo(source.getOrderNo());
		result.setIssueDate(source.getIssueDate());
		result.setVersion(source.getVersion());
		result.setLines(new ArrayList<OrderLine>());
		mapOrderLines(source.getOrderLines(), result.getLines(), result);
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
		mapOrderLineParameters(old.getParameterList(), ol.getOrderLineParameterList(), ol);
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
		
		olp.setParameter(parameterService.get(olpd.getpId()));
		olp.setValue(olpd.getpValue());
		olp.setOrderLine(orderLine);
		olp.setVersion(olp.getVersion());
		olp.setUnitOfMeasurement(uomService.get(olpd.getpUOM()));
	}

}
