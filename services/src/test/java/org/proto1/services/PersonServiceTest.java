package org.proto1.services;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.proto1.domain.Person;
import org.proto1.repository.PersonRepository;
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
