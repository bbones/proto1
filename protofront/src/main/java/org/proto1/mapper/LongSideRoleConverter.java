package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.SideRole;
import org.proto1.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class LongSideRoleConverter extends DozerConverter<Long, SideRole> {
	
	@Autowired
	ContractService contractService;

	public LongSideRoleConverter() {
		super(Long.class, SideRole.class);
	}

	@Override
	public SideRole convertTo(Long source, SideRole destination) {
		return contractService.getRole(source);
	}

	@Override
	public Long convertFrom(SideRole source, Long destination) {
		return source.getId();
	}

}
