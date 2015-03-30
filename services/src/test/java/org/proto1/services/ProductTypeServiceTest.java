/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.proto1.domain.product.ProductType;
import org.proto1.repository.ProductTypeRepository;
import org.proto1.services.product.ProductTypeService;
import org.proto1.services.product.ProductTypeServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/product.xml"})
public class ProductTypeServiceTest extends AbstractJUnit4SpringContextTests  {

	ProductTypeService pds = new ProductTypeServiceBean();
	
	@Autowired
	ProductType ironProduct, steelProduct, rolledProduct, castedProduct;
	
	@Test
	public void testGetTree() {
		ProductTypeRepository ptrep = createMock(ProductTypeRepository.class);
		expect(ptrep.getById(1L)).andReturn(ironProduct);
		replay(ptrep);
		pds.setProductTypeRepository(ptrep);
		ProductType pt = pds.getNodeById(1L);
		assertEquals(pt.getProductTypeNames().size(), 2);
	}

	@Test
	@Ignore
	public void testGetByParentTypeId() {
		ProductTypeRepository ptrep = createMock(ProductTypeRepository.class);
		List<ProductType> ptl = new ArrayList<ProductType>();
		ptl.add(ironProduct);
		expect(ptrep.getByParentTypeId(null)).andReturn(ptl);
		replay(ptrep);
		
		List<ProductType> ptl1 = pds.getByParentTypeId(null);
		assertEquals(1, ptl1.size());

	}
}
