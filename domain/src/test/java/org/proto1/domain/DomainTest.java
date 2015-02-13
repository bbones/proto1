package org.proto1.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"file:src/main/resources/META-INF/domain.xml"})
public class DomainTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	Person pva;
	
	@Autowired
	Enterprise isd;
	
	@Autowired
	Contract contract;
	
	@Test
	public void testParty() {
		assertNotNull(pva);
		assertNotNull(isd);
		assertNotNull(contract);
	}
	
	@Test
	public void testContract() {
		
	}

}
