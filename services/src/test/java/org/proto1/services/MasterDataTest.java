/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.proto1.domain.Language;
import org.proto1.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:/META-INF/domain.xml" })
public class MasterDataTest extends AbstractJUnit4SpringContextTests {
	MasterDataService masterDataService = new MasterDataServiceBean();

	@Autowired
	Language english, russian, ukrainian;

	@Test
	public void testGetLanguageList() {
		LanguageRepository lrep = createMock(LanguageRepository.class);
		List<Language> languageList = Arrays.asList(english, russian, ukrainian);
		expect(lrep.findAll()).andReturn(languageList);
		replay(lrep);
		masterDataService.setLanguageRepository(lrep);
		List<Language> langlist = masterDataService.getLanguageList();
		assertEquals(3, langlist.size());
	}
}
