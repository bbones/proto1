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

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.proto1.domain.party.Person;
import org.proto1.repository.party.PersonRepository;
import org.proto1.services.party.PersonService;
import org.proto1.services.party.PersonServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/domain.xml"})
public class PersonServiceTest extends AbstractJUnit4SpringContextTests {

	PersonService personService = new PersonServiceBean();
	
	@Autowired
	private Person pva;

	@Test
	public void testGetPersonById() {
		PersonRepository erep = createMock(PersonRepository.class);
		expect(erep.findOne(1L)).andReturn(pva);
		replay(erep);
		personService.setPersonRepository(erep);
		assertEquals(pva, personService.getById(1L));
	}
	
	@Test
	public void testSave() {
		PersonRepository erep = createMock(PersonRepository.class);
		expect(erep.save(pva)).andReturn(pva);
		replay(erep);
		personService.setPersonRepository(erep);
		assertEquals(pva, personService.save(pva));
	}
	
	@Test
	public void testDelete() {
		PersonRepository erep = createMock(PersonRepository.class);
		erep.delete(pva.getId());
		replay(erep);
		personService.setPersonRepository(erep);
		personService.delete(pva.getId());
		
	}
}
