package org.proto1.services.integration;

import org.apache.lucene.queryparser.xml.FilterBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proto1.config.TestAppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class})
public class EnterpriseSearchTest {
	private final Logger log = LoggerFactory.getLogger(EnterpriseSearchTest.class);
	@Autowired
	Client esclient;

	@Before
	@Ignore
	public void setUp() throws IOException {
		// on startup

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
	@Ignore
	public void testMatchQuery() {
		SearchResponse response = esclient.prepareSearch("proto1").setTypes("enterprise")
				.setQuery(QueryBuilders.matchQuery("names.name", "Duferco")).get();
		log.debug(response.toString());
	}
	
	@Test
	public void testBoolQuery() {
		QueryBuilder qb = QueryBuilders.boolQuery()
				.must(QueryBuilders.wildcardQuery("POST_INDEX", "83*"))
				.must(QueryBuilders.matchQuery("OKPO", "34225325"));
		log.debug(qb.toString());
		SearchResponse response = esclient.prepareSearch("test").setTypes("enterprise")
				.setQuery(qb).get();
		log.debug(response.toString());
	}
	

	@Test
	@Ignore
	public void testQuery() {
		SearchResponse response = esclient.prepareSearch("proto1").setTypes("enterprise")
				.setQuery(QueryBuilders.queryStringQuery("Duferco")).get();
		log.debug("+++++ FOUND +++++++   " + response.getHits().getTotalHits());
		for (SearchHit sh : response.getHits().getHits()) {
			Map<String,Object> searchHitSource=sh.getSource();
			log.debug(searchHitSource.toString());
		}
			
	}

}