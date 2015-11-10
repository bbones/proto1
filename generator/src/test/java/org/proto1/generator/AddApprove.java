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
package org.proto1.generator;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.proto1.domain.ApproveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:/META-INF/approve.xml" })
public class AddApprove extends AbstractJUnit4SpringContextTests {
	protected final Log logger = LogFactory.getLog(getClass());

	private EntityManagerFactory emf;
	private EntityManager em;

	@Autowired
	ApproveType contractApproveType;

	@Test
	public void test() {

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", "bbones");
		properties.put("javax.persistence.jdbc.password", "bb");
		emf = Persistence.createEntityManagerFactory("proto1", properties);
		em = emf.createEntityManager();

		em.getTransaction().begin();

		em.persist(contractApproveType);

		em.flush();
		em.getTransaction().commit();

	}

}
