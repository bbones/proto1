package org.proto1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.common.settings.Settings;

@Configuration
public class TestElasticConfig {
	
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(nodeBuilder()
        		.clusterName("elasticsearch")
        		.settings(Settings.settingsBuilder().put("path.home", "e:/es")).local(true).node().client());
    }

}

