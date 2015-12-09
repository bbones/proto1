package org.proto1.services.integration;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.proto1.config.TestAppConfig;
import org.proto1.config.TestElasticConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, TestElasticConfig.class})
public class ElasticSpringDataTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
