package org.proto1.services;

import static org.easymock.EasyMock.*;
//import static org.junit.Assert.*;

import org.junit.Test;
import org.proto1.domain.Railway;
import org.proto1.repository.RailwayRepository;
import org.proto1.services.RailwayService;
import org.proto1.services.RailwayServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/railway.xml"})
public class RailwayServiceTest extends AbstractJUnit4SpringContextTests {
	
	RailwayService railwayService = new RailwayServiceBean();
	
	@Autowired
	private Railway rw43;
	
	@Test
	public void testSave() {
		RailwayRepository erep = createMock(RailwayRepository.class);
		expect(erep.save(rw43)).andReturn(rw43);
		replay(erep);
		//railwayService.setRailwayRepository(erep);
		//assertEquals(rw43, railwayService.save(rw43));
	}

}
