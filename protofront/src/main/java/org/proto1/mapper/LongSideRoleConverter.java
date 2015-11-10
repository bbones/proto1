/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
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
