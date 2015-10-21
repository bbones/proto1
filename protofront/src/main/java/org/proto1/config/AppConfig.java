package org.proto1.config;

import java.io.IOException;

import org.dozer.spring.DozerBeanMapperFactoryBean;
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

}
