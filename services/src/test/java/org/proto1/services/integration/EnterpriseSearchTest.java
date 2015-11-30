package org.proto1.services.integration;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proto1.config.TestAppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class})
public class EnterpriseSearchTest {
	private final Logger log = LoggerFactory.getLogger(EnterpriseSearchTest.class);
	@Autowired
	Client esclient;

	@Before
	public void setUp() throws IOException {
		// on startup

		log.debug("Client started");
		
		IndexResponse response = esclient.prepareIndex("proto1", "enterprise", "1")
		        .setSource(jsonBuilder()
		                    .startObject()
					        .field("id", "1")
					        .field("names")
					        		.startArray()
					        			.startObject()
					        			.field("language", "English")
					        			.field("name", "AMK")
					        			.endObject()
					        			.startObject()
					        			.field("language", "Russian")
					        			.field("name", "ПАО АМК")
					        			.endObject()
					        		.endArray()
		                    .endObject()
		                  )
		        .get();
		log.debug(response.toString());
		// on shutdown

		
	}
	
	@Test
	public void testMatchQuery() {
		SearchResponse response = esclient.prepareSearch("proto1").setTypes("enterprise")
				.setQuery(QueryBuilders.matchQuery("names.name", "Duferco")).get();
		log.debug(response.toString());
	}
	
	@Test
	public void testQuery() {
		SearchResponse response = esclient.prepareSearch("proto1").setTypes("enterprise")
				.setQuery(QueryBuilders.queryStringQuery("Duferco")).get();
		log.debug(response.toString());
	}

}