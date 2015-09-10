package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.Railway;
import org.proto1.services.RailwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongRailwayConverter extends DozerConverter<Long, Railway> {
	
	@Autowired
	RailwayService railwayService;
	
	public LongRailwayConverter() {
		super(Long.class, Railway.class);
	}

	@Override
	public Railway convertTo(Long source, Railway destination) {
		return railwayService.getRailway(source);
	}

	@Override
	public Long convertFrom(Railway source, Long destination) {
		return source.getId();
	}
	
}
