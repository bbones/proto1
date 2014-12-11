package org.proto1.services;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.proto1.domain.product.Product;
import org.proto1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"file:src/main/resources/domain.xml"})
public class ProductServiceTest extends AbstractJUnit4SpringContextTests  {

	private ProductService productService = new ProductServiceBean();
	
	@Autowired
	private Product steelPlate;
	
	@Test
	public void testGetProductByID() {
		ProductRepository prep = createMock(ProductRepository.class);
		expect(prep.findOne(1L)).andReturn(steelPlate);
		replay(prep);
		productService.setProductRepository(prep);
		assertEquals(steelPlate, productService.getById(1L));
	}
	
	public void testSave() {
		ProductRepository prep = createMock(ProductRepository.class);
		expect(prep.save(steelPlate)).andReturn(steelPlate);
		replay(prep);
		productService.setProductRepository(prep);
		assertEquals(steelPlate, productService.save(steelPlate));
	}
	
	@Test
	public void testDelete() {
		ProductRepository prep = createMock(ProductRepository.class);
		prep.delete(steelPlate.getId());
		replay(prep);
		productService.setProductRepository(prep);
		productService.delete(steelPlate.getId());
		
	}
	

}
