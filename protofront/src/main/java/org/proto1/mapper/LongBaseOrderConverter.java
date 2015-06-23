package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.order.BaseOrder;
import org.proto1.domain.order.SalesOrder;
import org.proto1.services.order.BaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class LongBaseOrderConverter <T extends BaseOrder> extends DozerConverter<Long, T>{

	@Autowired
	BaseOrderService<T> orderService;
	
	public LongBaseOrderConverter(Class<Long> prototypeA, Class<T> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public T convertTo(Long source, T destination) {
		return orderService.get(source);
	}

	@Override
	public Long convertFrom(T source, Long destination) {
		return source.getId();
	}

}
