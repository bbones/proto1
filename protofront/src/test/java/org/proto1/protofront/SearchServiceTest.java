package org.proto1.protofront;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proto1.config.AppConfig;
import org.proto1.config.PersistenceConfig;
import org.proto1.config.WebConfig;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, PersistenceConfig.class,  WebConfig.class })
@WebAppConfiguration
public class SearchServiceTest extends AbstractJUnit4SpringContextTests {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	SearchController searchController;

	@Test
	public void testEmbedded() {
		Map<String,Object> example = new HashMap<String, Object>();
		example.put("enterprise.id", 45L);
		for(EnterpriseName entn : searchController.getList(1L, example, 1, 10)) {
			logger.debug(entn.getName() + " " + entn.getEnterprise().getId());
		}
	}

	@Test
	public void testDirect() {
		Map<String,Object> example = new HashMap<String, Object>();
		example.put("id", 47L);
		for(EnterpriseName entn : searchController.getList(1L, example, 1, 10)) {
			logger.debug(entn.getName() + " " + entn.getEnterprise().getId());
		}
	}

}
