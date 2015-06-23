package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.services.UnitOfMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongUOMConverter extends DozerConverter<Long, UnitOfMeasurement> {
	
	@Autowired
	UnitOfMeasurementService unitOfMeasurementService;

	public LongUOMConverter() {
		super(Long.class, UnitOfMeasurement.class);
	}

	@Override
	public UnitOfMeasurement convertTo(Long source,
			UnitOfMeasurement destination) {
		return unitOfMeasurementService.get(source);
	}

	@Override
	public Long convertFrom(UnitOfMeasurement source, Long destination) {
		return source.getId();
	}

}
