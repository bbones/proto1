/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import org.junit.Ignore;
import org.junit.Test;
import org.proto1.domain.Language;
import org.proto1.domain.product.ProductType;
import org.proto1.domain.product.ProductTypeName;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.proto1.repository.LanguageRepository;
import org.proto1.services.MasterDataService;
import org.proto1.services.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:/META-INF/domain.xml", "classpath:/META-INF/product.xml", "classpath:applicationContext.xml" })
public class ProductTypeServiceTest  extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	ProductTypeService pds;
	
	@Autowired
	MasterDataService mds;

	@Autowired
	ProductType ironProduct;
	
	@Autowired
	LanguageRepository lr;
	
	@Autowired
	Language english, russian, ukrainian;
	@Autowired
	ProductTypeName 
		ironProductTypeNameEnglish, ironProductTypeNameRussian;

	@Test
	@Ignore
	@Transactional()
	public void test() {
		ProductType pt = new ProductType();
		ProductType parent = pds.get(1L);
		pt.setParentType(parent);
		// pt = pds.save(pt);
		for (LocalizedStringConstant name : mds.getRequiredLocalizedStringList("productType")) {
			ProductTypeName pdn = new ProductTypeName();
			pdn.setLanguage(name.getLanguage());
			pdn.setProductType(pt);
			pdn.setName(name.getText());
			pt.getProductTypeNames().add(pdn);
		}
		pt = pds.save(pt);
	}

	@Test
	public void testSave() {
		lr.save(english);
		lr.save(russian);
		lr.save(ukrainian);
		pds.save(ironProduct);
	}
}
