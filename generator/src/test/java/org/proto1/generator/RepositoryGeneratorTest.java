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
