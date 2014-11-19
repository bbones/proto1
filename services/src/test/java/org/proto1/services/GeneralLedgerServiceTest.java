package org.proto1.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"file:src/main/resources/domain.xml"})
public class GeneralLedgerServiceTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void test() {
		// fail("Not yet implemented");
	}

}
