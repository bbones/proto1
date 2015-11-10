/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
package org.proto1.services;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.proto1.domain.product.ProductType;
import org.proto1.repository.product.ProductTypeRepository;
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
	@Ignore
	public void testGetTree() {
		ProductTypeRepository ptrep = createMock(ProductTypeRepository.class);
		expect(ptrep.findOne(1L)).andReturn(ironProduct);
		replay(ptrep);
		pds.setProductTypeRepository(ptrep);
		ProductType pt = pds.get(1L);
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
