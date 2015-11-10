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
package org.proto1.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.proto1.domain.party.Enterprise;
import org.proto1.repository.party.EnterpriseRepository;
import org.proto1.services.party.EnterpriseServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.easymock.EasyMock.*;

@ContextConfiguration(locations={"classpath:/META-INF/domain.xml"})
public class EnterpriseServiceTest extends AbstractJUnit4SpringContextTests {
	
	EnterpriseServiceBean enterpriseService = new EnterpriseServiceBean();
	
	@Autowired
	private Enterprise isd;

	@Test
	public void testGetEnterpriseById() {
		EnterpriseRepository erep = createMock(EnterpriseRepository.class);
		expect(erep.findOne(1L)).andReturn(isd);
		replay(erep);
		enterpriseService.setEnterpriseRepository(erep);
		assertEquals(isd, enterpriseService.get(1L));
	}
	
	@Test
	public void testSave() {
		EnterpriseRepository erep = createMock(EnterpriseRepository.class);
		expect(erep.save(isd)).andReturn(isd);
		replay(erep);
		enterpriseService.setEnterpriseRepository(erep);
		assertEquals(isd, enterpriseService.save(isd));
	}
	
	@Test
	public void testDelete() {
		EnterpriseRepository erep = createMock(EnterpriseRepository.class);
		erep.delete(isd.getId());
		replay(erep);
		enterpriseService.setEnterpriseRepository(erep);
		enterpriseService.delete(isd.getId());
		
	}
}
