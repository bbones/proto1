/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.tools;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.ApplicationContext;

public class DomainPersister {
	private ApplicationContext applicationContext;
	
	private EntityManager entityManager;
	
	public DomainPersister(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void persist(List<String> beanNames) {
		for (String beanname : beanNames) {
			Object bean = applicationContext.getBean(beanname);
			entityManager.persist(bean);
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
