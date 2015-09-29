/**
 * TODO Access Rules!!!
 */
package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.order.OrderLine;
import org.proto1.services.order.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongOrderLineConverter extends DozerConverter<Long, OrderLine> {

	@Autowired
	OrderLineService orderLineService;
	
	public LongOrderLineConverter() {
		super(Long.class, OrderLine.class);
	}

	@Override
	public OrderLine convertTo(Long source, OrderLine destination) {
		if (source != null)
			return orderLineService.get(source);
		else
			return null;
	}

	@Override
	public Long convertFrom(OrderLine source, Long destination) {
		return source.getId();
	}

}
