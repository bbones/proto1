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
package org.proto1.generator;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/domain.xml"})
public class RepositoryGeneratorTest extends AbstractJUnit4SpringContextTests  {
	
	protected final Log logger = LogFactory.getLog(getClass());

	RepositoryGenerator repositoryGenerator;
	
	@Before
	public void setUp() {
		repositoryGenerator = new RepositoryGenerator(applicationContext, "org.proto1.domain");
	}
	
	@Test
	public void testGetContextInfo() {
		logger.debug(repositoryGenerator.getContextInfo());
	}
	
	@Test
	public void testGenerateRepoitoryCode() throws IOException {
		repositoryGenerator.generateRepositoryCode("repository.vm", "com.proto1.server.repository");
	}

}
