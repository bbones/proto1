package org.proto1.protofront;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.proto1.config.AppConfig;
import org.proto1.config.PersistenceConfig;
import org.proto1.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, PersistenceConfig.class,  WebConfig.class })
@WebAppConfiguration
public class SearchServiceTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	SearchController searchController;

	@Test
	public void test() {
		Map<String,Object> example = new HashMap<String, Object>();
		example.put("name", "ISD%");
		assertNull(searchController.getList(example));
	}

}
