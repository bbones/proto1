package org.proto1.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"file:src/main/resources/META-INF/domain.xml"})
public class EnterpriseServiceTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	EnterpriseService enterpriseService;

	@Test
	public void testGetEnterpriseById() {
		enterpriseService.getEnterpriseById(1L);
	}

}
