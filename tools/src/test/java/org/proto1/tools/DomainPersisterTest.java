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

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"domain.xml"})
public class DomainPersisterTest extends AbstractJUnit4SpringContextTests {
	
	@Test
	public void testPersist() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "proto1" );
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		DomainPersister dp = new DomainPersister(applicationContext);
		dp.setEntityManager(entityManager);
		ArrayList<String> list = new ArrayList<String>(); 
		list.add("seller");
		list.add("buyer");
		dp.persist(list);
	}

}
