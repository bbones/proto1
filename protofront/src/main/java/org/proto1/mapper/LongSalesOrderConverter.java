package org.proto1.mapper;

import org.proto1.domain.order.SalesOrder;
import org.springframework.stereotype.Component;

@Component
public class LongSalesOrderConverter extends LongBaseOrderConverter<SalesOrder> {

	public LongSalesOrderConverter() {
		super(Long.class, SalesOrder.class);
	}

}
