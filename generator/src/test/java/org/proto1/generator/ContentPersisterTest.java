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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.proto1.domain.Contract;
import org.proto1.domain.ContractSide;
import org.proto1.domain.Enterprise;
import org.proto1.domain.Language;
import org.proto1.domain.Person;
import org.proto1.domain.SideRole;
import org.proto1.domain.product.Parameter;
import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductType;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/product.xml","classpath:/META-INF/utility.xml"})
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
	ProductType 
		ironProduct, steelProduct, rolledProduct, castedProduct,
			cokeProduct, cokeMainProduct, cokeByProduct,
				iormProduct, nonAgglomeratedIORM, agglomeratedIORM;
	
	@Autowired
	Product 
		steelPlate, slab, pigIron,
		coke, cokeBreese, coalTar,
		ironOreConcentrate, ironOre, ironOrePellet, ironOreSinter;
	
	@Autowired
	Parameter
		gradeOfSteel, gradeOfCoke, ironContent, thickness, width, length;
	
	@Autowired
	Language english, russian, ukrainian;
	
	@Autowired
	LocalizedStringConstant defaultEnglishProductType, defaultRussianProductType, defaultUkrainianProductType;

	@Before
	public void startUp () {

		Map<String, String> properties = new HashMap<String, String>();
		  properties.put("javax.persistence.jdbc.user", "sa");
		  properties.put("javax.persistence.jdbc.password", "");
		emf = Persistence.createEntityManagerFactory("proto1", properties);	
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		
		persistLanguage();
		persistPerson();
		persistEnterprise();
		persistSideRole();
		persistContract();
		persistProductType();
		persistParameters();
		persistProduct();
		persistLocalizedStringConstant();

		em.getTransaction().commit();

	}
	
	@After
	public void tearDown() {
		em.close();
		emf.close();
	}

	private void persistParameters() {
		
		em.persist(thickness);
		em.persist(width);
		em.persist(length);
		em.persist(gradeOfSteel);
		em.persist(gradeOfCoke);
		em.persist(ironContent);
		
	}

	private void persistLocalizedStringConstant() {
		em.persist(defaultEnglishProductType);
		em.persist(defaultRussianProductType);
		em.persist(defaultUkrainianProductType);
	}

	private void persistProduct() {
		// Products
		em.persist(steelPlate);
		em.persist(slab);
		em.persist(pigIron);
		
		em.persist(coke);
		em.persist(cokeBreese);
		em.persist(coalTar);
		
		em.persist(ironOreConcentrate);
		em.persist(ironOre);
		em.persist(ironOrePellet);
		em.persist(ironOreSinter);
	}

	private void persistProductType() {
		// Product Types
		em.persist(ironProduct); 
		
		em.persist(steelProduct); 
				
		em.persist(rolledProduct);
		
		em.persist(castedProduct);
		
		em.persist(cokeProduct);
		em.persist(cokeMainProduct);
		em.persist(cokeByProduct);
		
		em.persist(iormProduct);
		em.persist(nonAgglomeratedIORM);
		em.persist(agglomeratedIORM);
	}

	private void persistContract() {
		em.persist(pvaSide);
		em.persist(isdSide);
		
		em.persist(contract);
	}

	private void persistSideRole() {
		em.persist(seller);
		em.persist(buyer);
	}

	private void persistEnterprise() {
		em.persist(isd);
	}

	private void persistPerson() {
		em.persist(pva);
		em.persist(mark);
		em.persist(gleb);
	}

	private void persistLanguage() {
		em.persist(english);
		em.persist(ukrainian);
		em.persist(russian);
	}

	@Test 
	public void testQuery() {

		Query query = em.createNamedQuery("partyList");
		// query.setParameter("pattern", "");
		List<?> result = query.getResultList();
		
		for (Object object : result) {
			logger.debug(object);
		}
		
	}
}
