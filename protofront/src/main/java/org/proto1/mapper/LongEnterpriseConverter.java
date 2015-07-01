package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.party.Enterprise;
import org.proto1.services.party.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongEnterpriseConverter extends DozerConverter<Long, Enterprise> {
	
	@Autowired
	EnterpriseService enterpriseService;

	public LongEnterpriseConverter() {
		super(Long.class, Enterprise.class);
	}

	@Override
	public Enterprise convertTo(Long source, Enterprise destination) {
		return enterpriseService.get(source);
	}

	@Override
	public Long convertFrom(Enterprise source, Long destination) {
		return source.getId();
	}

}
