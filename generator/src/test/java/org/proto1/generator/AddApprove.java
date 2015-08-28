package org.proto1.generator;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.proto1.domain.ApproveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={
		"classpath:/META-INF/approve.xml"
	})
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
