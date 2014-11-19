package org.proto1.tools;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"domain.xml"})
public class ContextUtilityTest extends AbstractJUnit4SpringContextTests  {
	
	@Test
	public void testGetClassList() {
		logger.debug("Class list");
		for(Class<?> clazz : ContextUtility.getClassList(applicationContext, "org.proto1.domain")) {
			logger.debug("\t" +clazz.getCanonicalName());
		}
	}
	

}
