/**
 * TODO Direct Repository call!!! Need different service
 */
package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.order.OrderLine;
import org.proto1.repository.order.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongOrderLineConverter extends DozerConverter<Long, OrderLine> {

	@Autowired
	OrderLineRepository orderLineRepository;
	
	public LongOrderLineConverter() {
		super(Long.class, OrderLine.class);
	}

	@Override
	public OrderLine convertTo(Long source, OrderLine destination) {
		return orderLineRepository.findOne(source);
	}

	@Override
	public Long convertFrom(OrderLine source, Long destination) {
		return source.getId();
	}

}
