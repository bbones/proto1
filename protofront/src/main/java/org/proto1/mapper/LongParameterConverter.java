package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.product.Parameter;
import org.proto1.services.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongParameterConverter extends DozerConverter<Long, Parameter> {
	
	@Autowired
	ParameterService parameterService;

	public LongParameterConverter() {
		super(Long.class, Parameter.class);
	}

	@Override
	public Parameter convertTo(Long source, Parameter destination) {
		return parameterService.get(source);
	}

	@Override
	public Long convertFrom(Parameter source, Long destination) {
		return source.getId();
	}

}
