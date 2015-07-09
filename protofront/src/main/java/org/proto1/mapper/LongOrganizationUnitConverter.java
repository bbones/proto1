package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.party.OrganizationUnit;
import org.proto1.services.party.OrganizationUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongOrganizationUnitConverter extends DozerConverter<Long, OrganizationUnit>{

	@Autowired
	OrganizationUnitService organizationUnitService; 
	
	public LongOrganizationUnitConverter() {
		super(Long.class, OrganizationUnit.class);
	}

	@Override
	public OrganizationUnit convertTo(Long source, OrganizationUnit destination) {
		return organizationUnitService.get(source);
	}

	@Override
	public Long convertFrom(OrganizationUnit source, Long destination) {
		return source.getId();
	}

}
