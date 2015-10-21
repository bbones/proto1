package org.proto1.config;

import org.activiti.rest.common.application.ContentTypeResolver;
import org.activiti.rest.common.application.DefaultContentTypeResolver;
import org.activiti.rest.service.api.RestResponseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.activiti.rest"})
public class ActivitiRestConfiguration {

    @Bean()
    public RestResponseFactory restResponseFactory() {
      RestResponseFactory restResponseFactory = new RestResponseFactory();
      return restResponseFactory;
    }

    @Bean()
    public ContentTypeResolver contentTypeResolver() {
      ContentTypeResolver resolver = new DefaultContentTypeResolver();
      return resolver;
    }
}