package org.proto1.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.proto1.domain.Enterprise;
import org.proto1.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.easymock.EasyMock.*;

@ContextConfiguration(locations={"file:src/main/resources/domain.xml"})
public class EnterpriseServiceTest extends AbstractJUnit4SpringContextTests {
	
	EnterpriseService enterpriseService = new EnterpriseServiceBean();
	
	@Autowired
	private Enterprise isd;

	@Test
	public void testGetEnterpriseById() {
		EnterpriseRepository erep = createMock(EnterpriseRepository.class);
		expect(erep.findOne(1L)).andReturn(isd);
		replay(erep);
		enterpriseService.setEnterpriseRepository(erep);
		assertEquals(isd, enterpriseService.getEnterpriseById(1L));
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