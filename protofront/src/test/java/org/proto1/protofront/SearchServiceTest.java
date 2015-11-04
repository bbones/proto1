package org.proto1.protofront;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, PersistenceConfig.class,  WebConfig.class })
@WebAppConfiguration
public class SearchServiceTest extends AbstractJUnit4SpringContextTests {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	EnterpriseController searchController;

	@Test
	public void testEmbedded() throws JsonProcessingException {
		Map<String,Object> example = new HashMap<String, Object>();
		example.put("enterprise.id", 45L);
		logger.debug(searchController.getListBE(1L, example, 1, 10));
	}

	@Test
	public void testDirect() throws JsonProcessingException {
		Map<String,Object> example = new HashMap<String, Object>();
		example.put("name", "%ИСД%");
		logger.debug(searchController.getListBE(1L, example, 1, 10));
	}

}
