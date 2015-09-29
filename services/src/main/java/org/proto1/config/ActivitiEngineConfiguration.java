package org.proto1.config;


import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class ActivitiEngineConfiguration {
	  @Autowired
	  protected DataSource dataSource;
	  
	  @Autowired
	  protected PlatformTransactionManager transactionManager;
	  
	  @Autowired
	  protected EntityManagerFactory entityManagerFactory;
	  @SuppressWarnings("unused")
	  private final Logger log = LoggerFactory.getLogger(ActivitiEngineConfiguration.class);
	  @Autowired
	  private ResourcePatternResolver resourceResolver;
	  
	  @Bean(name="processEngineFactoryBean")
	  public ProcessEngineFactoryBean processEngineFactoryBean() throws IOException {
	    ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
	    factoryBean.setProcessEngineConfiguration(processEngineConfiguration());
	    return factoryBean;
	  }
	  
	  @Bean(name="processEngine")
	  public ProcessEngine processEngine() {
	    // Safe to call the getObject() on the @Bean annotated processEngineFactoryBean(), will be
	    // the fully initialized object instanced from the factory and will NOT be created more than once
	    try {
	      return processEngineFactoryBean().getObject();
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	  }
	  
	  @Bean(name="processEngineConfiguration")
	  public ProcessEngineConfigurationImpl processEngineConfiguration() throws IOException {
	  	SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
	  	processEngineConfiguration.setDataSource(dataSource);
	  	processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
	  	processEngineConfiguration.setTransactionManager(transactionManager);
	  	processEngineConfiguration.setJobExecutorActivate(false);
	  	processEngineConfiguration.setAsyncExecutorEnabled(true);
	  	processEngineConfiguration.setAsyncExecutorActivate(false);
	  	processEngineConfiguration.setJpaEntityManagerFactory(entityManagerFactory);
	  	processEngineConfiguration.setJpaHandleTransaction(false);
	  	processEngineConfiguration.setHistoryLevel(HistoryLevel.FULL);	  
	    processEngineConfiguration.setDeploymentResources(processResources());  	
	    //processEngineConfiguration.setMailServerHost("");

	  	return processEngineConfiguration;
	  }
	  
	  @Bean
	  public RepositoryService repositoryService() {
	  	return processEngine().getRepositoryService();
	  }
	  
	  @Bean
	  public RuntimeService runtimeService() {
	  	return processEngine().getRuntimeService();
	  }
	  
	  @Bean
	  public TaskService taskService() {
	  	return processEngine().getTaskService();
	  }
	  
	  @Bean
	  public HistoryService historyService() {
	  	return processEngine().getHistoryService();
	  }
	  
	  @Bean
	  public FormService formService() {
	  	return processEngine().getFormService();
	  }
	  
	  @Bean
	  public IdentityService identityService() {
	  	return processEngine().getIdentityService();
	  }
	  
	  @Bean
	  public ManagementService managementService() {
	  	return processEngine().getManagementService();
	  }
	  
	  protected Resource[] processResources() throws IOException { 
		           return resourceResolver.getResources("classpath*:/process/*.bpmn20.xml") ; 
		       } 

	}
