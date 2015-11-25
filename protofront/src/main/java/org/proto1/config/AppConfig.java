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
package org.proto1.config;

import static org.elasticsearch.node.NodeBuilder.*;

import java.io.IOException;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@ComponentScan({"org.proto1"})

public class AppConfig {
	@Autowired
	private ApplicationContext context;

	@Bean
	public DozerBeanMapperFactoryBean mapper() throws IOException {
		DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean();
		Resource[] mappingFiles = ((ResourcePatternResolver) context)
				.getResources("classpath*:META-INF/dozer-conf/*.xml");
		mapper.setMappingFiles(mappingFiles);
		return mapper;
	}

	@Bean
	public Node node() {
		Node node = nodeBuilder().clusterName("elasticsearch")
		        .settings(Settings.settingsBuilder()
		        		.put("path.home", "e:/es"))
		        .client(true)
		        .node();
		return node;
	}
	
	@Bean
	public Client esclient() {
		return node().client();
	}

}
