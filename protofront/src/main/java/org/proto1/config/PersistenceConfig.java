package org.proto1.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="org.proto1.repository")
public class PersistenceConfig 
{
	@Autowired
	private Environment env;
 
	private final Logger log = LoggerFactory.getLogger(PersistenceConfig.class);
	
	@Bean
	public PlatformTransactionManager transactionManager()
	{
		EntityManagerFactory factory = entityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}
 
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();	 
		factory.setDataSource(dataSource());
		factory.setPackagesToScan("org.proto1.domain");
		factory.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
		factory.setPersistenceUnitName("proto1");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(Boolean.FALSE);
		vendorAdapter.setGenerateDdl(Boolean.TRUE);
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		factory.setJpaVendorAdapter(vendorAdapter);
 
	/*	Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		factory.setJpaProperties(jpaProperties);
    */
		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return factory;
			
	}
 
	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator()
	{
		return new HibernateExceptionTranslator();
	}

	
	@Bean
	public DataSource dataSource()
	{			
	//    log.debug("PersistenceConfig.dataSource()");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
		dataSource.setUsername("bbones");
		dataSource.setPassword("bb");
		return dataSource;
	}
	
}