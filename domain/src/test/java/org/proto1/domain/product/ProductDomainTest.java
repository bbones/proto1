package org.proto1.domain.product;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"file:src/main/resources/META-INF/product.xml"})
public class ProductDomainTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	ProductType ironProduct, steelProduct, rolledProduct, castedProduct;
	
	@Autowired
	Product steelPlate, slab, pigIron;

	@Test
	public void testDomain() {
		assertNotNull(steelPlate);
	}

}
