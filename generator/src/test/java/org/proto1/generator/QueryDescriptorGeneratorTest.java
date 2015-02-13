package org.proto1.generator;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.proto1.domain.Contract;
import org.proto1.domain.ContractSide;
import org.proto1.domain.SideRole;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.Party;
import org.proto1.domain.party.Person;
import org.proto1.generator.domain.FieldDescriptor;
import org.proto1.generator.domain.QueryDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/domain.xml"})
public class QueryDescriptorGeneratorTest extends AbstractJUnit4SpringContextTests{
	protected final Log logger = LogFactory.getLog(getClass());
	private EntityManagerFactory emf, toolsemf;
	private EntityManager em, toolsem;
	
	@Autowired
	Person pva, mark, gleb;
	
	@Autowired
	Enterprise isd;
	
	@Autowired
	SideRole seller, buyer;
	
	@Autowired
	ContractSide isdSide, pvaSide;
	
	@Autowired
	Contract contract;

	@Before
	public void startUp () {

		Map<String, String> properties = new HashMap<String, String>();
		  properties.put("javax.persistence.jdbc.user", "sa");
		  properties.put("javax.persistence.jdbc.password", "");
		emf = Persistence.createEntityManagerFactory("proto1", properties);	
		em = emf.createEntityManager();
		
		toolsemf = Persistence.createEntityManagerFactory("prototools", properties);	
		toolsem = toolsemf.createEntityManager();

		String[] deleteQueries = {
				"delete from ContractSide",
				"delete from Contract",
				"delete from Person",
				"delete from Enterprise"
		};

		em.getTransaction().begin();
		for (String string : deleteQueries) {
			Query q1 = em.createQuery(string);
			q1.executeUpdate();
			
		}
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		
		em.persist(pva);
		em.persist(mark);
		em.persist(gleb);
		
		em.persist(isd);

		em.persist(seller);
		em.persist(buyer);

		em.persist(pvaSide);
		em.persist(isdSide);
		
		em.persist(contract);

		
		em.getTransaction().commit();

	}


	@Test
	public void testQueryList() {
		// Set<Class<?>> classList = ContextUtility.getClassList(applicationContext, "org.proto1.domain");
		
		// for(Class<?> clazz : classList) {
		
		Class<?> clazz = Party.class;
		Annotation annotation = clazz.getAnnotation(NamedQueries.class);
		if (annotation != null) {
			NamedQueries nqs = (NamedQueries)annotation;
			for (NamedQuery nq : nqs.value()) { 
				QueryDescriptor queryDescriptor = new QueryDescriptor();
				queryDescriptor.setUpdateEntity(clazz.getCanonicalName());
				queryDescriptor.setQuery(nq.query());
				queryDescriptor.setFieldList(new LinkedList<FieldDescriptor>());
				logger.debug(clazz.getName() +":" +nq.name());
				Query query = em.createNamedQuery("partyList");
				@SuppressWarnings("unchecked")
				List<Map<String, ?>> result = query.getResultList();
				
				for (Map<String, ?> row : result) {
					logger.debug(row);
					for (String entry : row.keySet()) {
						logger.debug("->" + entry);
						FieldDescriptor fieldDescriptor = new FieldDescriptor();
						fieldDescriptor.setName(entry);
						fieldDescriptor.setType(row.get(entry).getClass().getCanonicalName());
						queryDescriptor.getFieldList().add(fieldDescriptor);
					}
					break;
				}
				
				toolsem.getTransaction().begin();
				toolsem.persist(queryDescriptor);
				toolsem.getTransaction().commit();
			}
		}
		
		// }
	}
	

	@After
	public void tearDown() {
		emf.close();
	}

}
