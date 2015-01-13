package org.proto1.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.proto1.domain.Contract;
import org.proto1.domain.ContractSide;
import org.proto1.domain.Enterprise;
import org.proto1.domain.Language;
import org.proto1.domain.Person;
import org.proto1.domain.SideRole;
import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductName;
import org.proto1.domain.product.ProductType;
import org.proto1.domain.product.ProductTypeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/product.xml"})
public class ContentPersisterTest extends AbstractJUnit4SpringContextTests{
	
	protected final Log logger = LogFactory.getLog(getClass());

	private EntityManagerFactory emf;
	private EntityManager em;
	
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
	
	@Autowired
	ProductType ironProduct, steelProduct, rolledProduct, castedProduct;
	
	@Autowired
	ProductTypeName 
		ironProductTypeNameEnglish, ironProductTypeNameRussian,
		steelProductTypeNameEnglish, steelProductTypeNameRussian,
		rolledProductTypeNameEnglish, rolledProductTypeNameRussian,
		castedProductTypeNameEnglish, castedProductTypeNameRussian;
	
	@Autowired
	Product steelPlate, slab, pigIron;
	
	@Autowired
	ProductName 
		steelPlateProductNameEnglish, steelPlateNameRussian,
		slabProductNameEnglish, slabProductNameRussian,
		pigIronProductNameEnglish, pigIronProductNameRussian;
	
	@Autowired
	Language english, russian, ukrainian;

	@Before
	public void startUp () {

		Map<String, String> properties = new HashMap<String, String>();
		  properties.put("javax.persistence.jdbc.user", "sa");
		  properties.put("javax.persistence.jdbc.password", "");
		emf = Persistence.createEntityManagerFactory("proto1", properties);	
		em = emf.createEntityManager();
		
		String[] deleteQueries = {
				"delete from ContractSide",
				"delete from Contract",
				"delete from Person",
				"delete from Enterprise",
				"delete from Product",
				"delete from Language"
		};

		em.getTransaction().begin();
		for (String string : deleteQueries) {
			Query q1 = em.createQuery(string);
			q1.executeUpdate();
			
		}
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		
		em.persist(english);
		em.persist(ukrainian);
		em.persist(russian);
		
		
		em.persist(pva);
		em.persist(mark);
		em.persist(gleb);
		
		em.persist(isd);

		em.persist(seller);
		em.persist(buyer);

		em.persist(pvaSide);
		em.persist(isdSide);
		
		em.persist(contract);
		
		// Product Types
		em.persist(ironProduct); 
		em.persist(ironProductTypeNameEnglish);
		em.persist(ironProductTypeNameRussian);
		
		em.persist(steelProduct); 
		em.persist(steelProductTypeNameEnglish);
		em.persist(steelProductTypeNameRussian);
				
		em.persist(rolledProduct);
		em.persist(rolledProductTypeNameEnglish);
		em.persist(rolledProductTypeNameRussian);
		
		em.persist(castedProduct);
		em.persist(castedProductTypeNameEnglish);
		em.persist(castedProductTypeNameRussian);
		
		// Products
		em.persist(steelPlate);
		em.persist(steelPlateProductNameEnglish);
		em.persist(steelPlateNameRussian);
		
		em.persist(slab);
		em.persist(slabProductNameEnglish);
		em.persist(slabProductNameRussian);

		em.persist(pigIron);
		em.persist(pigIronProductNameEnglish);
		em.persist(pigIronProductNameRussian);

		em.getTransaction().commit();

	}

	@Test 
	public void testQuery() {

		Query query = em.createNamedQuery("partyList");
		// query.setParameter("pattern", "");
		List<?> result = query.getResultList();
		
		for (Object object : result) {
			logger.debug(object);
		}
		
		emf.close();
		
	}
}
