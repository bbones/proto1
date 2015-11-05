/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
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
