package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.order.BaseOrder;
import org.proto1.domain.order.SalesOrder;
import org.proto1.protofront.order.SalesOrderController;
import org.proto1.services.order.BaseOrderService;
import org.proto1.services.order.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongSalesOrderConverter extends DozerConverter<Long, SalesOrder> {

	@Autowired
	SalesOrderService salesOrderService;
	
	public LongSalesOrderConverter() {
		super(Long.class, SalesOrder.class);
	}

	@Override
	public SalesOrder convertTo(Long source, SalesOrder destination) {
		return salesOrderService.get(source);
	}

	@Override
	public Long convertFrom(SalesOrder source, Long destination) {
		return source.getId();
	}

}
