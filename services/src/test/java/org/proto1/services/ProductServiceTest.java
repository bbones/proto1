/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.proto1.domain.Language;
import org.proto1.domain.product.Product;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.ProductRepository;
import org.proto1.services.product.ProductService;
import org.proto1.services.product.ProductServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/product.xml"})
public class ProductServiceTest extends AbstractJUnit4SpringContextTests  {

	private ProductService productService = new ProductServiceBean();
	
	@Autowired
	private Product steelPlate, slab;
	
	@Autowired
	private Language ukrainian, russian; 
	
	@Test
	public void testGetProductByID() {
		ProductRepository prep = createMock(ProductRepository.class);
		expect(prep.getById(1L)).andReturn(steelPlate);
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
	
	@Test
	@Ignore
	public void testSaveName() {
		ProductRepository prep = createMock(ProductRepository.class);
		expect(prep.getById(1L)).andReturn(steelPlate).times(2);
		expect(prep.save(steelPlate)).andReturn(steelPlate);
		replay(prep);
		
		LanguageRepository lrep = createMock(LanguageRepository.class);
		expect(lrep.findOne(3L)).andReturn(ukrainian);

		replay(lrep);
		
		productService.setProductRepository(prep);
		productService.setLanguageRepository(lrep);

		productService.saveProductName(null, 1L, 3L, "Плита горячекатана");
		Product prod = prep.getById(1L);
		assertEquals(3, prod.getProductNames().size());
	}
	
	@Test
	@Ignore
	public void testDeleteName() {
		ProductRepository prep = createMock(ProductRepository.class);
		expect(prep.getById(2L)).andReturn(slab).times(2);
		expect(prep.save(slab)).andReturn(slab);
		replay(prep);
		
		LanguageRepository lrep = createMock(LanguageRepository.class);
		expect(lrep.findOne(2L)).andReturn(russian);

		replay(lrep);
		
		productService.setProductRepository(prep);
		productService.setLanguageRepository(lrep);

		productService.deleteName(2L);
		
		assertEquals(1, productService.getById(2L).getProductNames().size());
	}

}
