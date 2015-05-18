package org.proto1.protofront;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:/META-INF/domain.xml", "classpath:/META-INF/product.xml", "classpath:applicationContext.xml" })
public class DTOAnnotationTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void test() {
		
	}

}
