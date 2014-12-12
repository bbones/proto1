package org.proto1.generator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/domain.xml"})
public class EntityDescriptorGeneratorTest extends AbstractJUnit4SpringContextTests  {
	protected final Log logger = LogFactory.getLog(getClass());

	@Test
	public void test() {
;
	}

}
