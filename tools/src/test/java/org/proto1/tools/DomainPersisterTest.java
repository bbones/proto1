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
