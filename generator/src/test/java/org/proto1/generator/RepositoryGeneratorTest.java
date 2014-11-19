package org.proto1.generator;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.proto1.domain.Contract;
import org.proto1.domain.Enterprise;
import org.proto1.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"/domain.xml"})
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
