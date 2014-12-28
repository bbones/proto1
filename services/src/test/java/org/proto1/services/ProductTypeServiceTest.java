package org.proto1.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/product.xml"})
public class ProductTypeServiceTest extends AbstractJUnit4SpringContextTests  {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
