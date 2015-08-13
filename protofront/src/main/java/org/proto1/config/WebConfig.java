package org.proto1.config;
 
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

 
/**
 * Web Configuration expose the all services
 * 
 * @author malalanayake
 * 
 */
@Configuration
@ComponentScan("org.proto1")
/*@ImportResource({ "classpath:META-INF/applicationContext.xml" })*/
@EnableWebMvc
public class WebConfig extends WebMvcConfigurationSupport{//extends WebMvcConfigurerAdapter {
 
    public WebConfig() {
        super();
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {      
        addDefaultHttpMessageConverters(converters);
        converters.add(jackson2Converter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2Converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        MediaType[] arr = new  MediaType[] { MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.TEXT_HTML };
        converter.setSupportedMediaTypes(Arrays.asList(arr)); 
        converter.setObjectMapper(objectMapper());
        
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper;
    }
    
  
  }
