package org.proto1.generator;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.proto1.domain.Contract;
import org.proto1.domain.ContractSide;
import org.proto1.domain.DimensionUnit;
import org.proto1.domain.Language;
import org.proto1.domain.SideRole;
import org.proto1.domain.order.SalesOrder;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.Person;
import org.proto1.domain.product.Parameter;
import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductParameter;
import org.proto1.domain.product.ProductType;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/product.xml", "classpath:/META-INF/order.xml","classpath:/META-INF/utility.xml"})
public class ContentPersisterTest extends AbstractJUnit4SpringContextTests{
	
	protected final Log logger = LogFactory.getLog(getClass());

	private EntityManagerFactory emf;
	private EntityManager em;
	
	@Autowired
	Person pva, mark, gleb;
	
	@Autowired
	Enterprise isd, amk, duferco;
	
	@Autowired
	SideRole seller, buyer;
	
	@Autowired
	ContractSide isdSide, pvaSide;
	
	@Autowired
	Contract contract;
	
	@Autowired
	ProductType 
		ironProduct, highCarbonIronProduct,  
		steelProduct, rolledProduct, flatRolledProduct, longRolledProduct, 
		castedProduct,
		cokeProduct, cokeMainProduct, cokeByProduct,
		iormProduct, nonAgglomeratedIORM, agglomeratedIORM;
	
	@Autowired
	Product 
		steelPlate, channel, iBeam, slab, pigIron, hotIron, 
		coke, cokeBreese, coalTar,
		ironOreConcentrate, ironOre, ironOrePellet, ironOreSinter;
	
	@Autowired
	Parameter
		gradeOfSteel, gradeOfCoke, ironContent, thickness, width, length;
	
	@Autowired
	Language english, russian, ukrainian;
	
	@Autowired
	DimensionUnit metricTonn, cubicMeter, kg;
	
	@Autowired
	LocalizedStringConstant defaultEnglishProductType, defaultRussianProductType, defaultUkrainianProductType;
	
	@Autowired
	SalesOrder salesOrder1;

	@Before
	public void startUp () {

		Map<String, String> properties = new HashMap<String, String>();
		  properties.put("javax.persistence.jdbc.user", "sa");
		  properties.put("javax.persistence.jdbc.password", "");
		emf = Persistence.createEntityManagerFactory("proto1", properties);	
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		persistLanguage();
		persistDimensionUnit();
		persistPerson();
		persistEnterprise();
		persistSideRole();
		persistContract();
		persistProductType();
		persistParameters();
		persistProduct();
		persistLocalizedStringConstant();
		
		persistOrder();

		em.getTransaction().commit();

	}
	
	private void persistDimensionUnit() {
		em.persist(kg);
		em.persist(metricTonn);
		em.persist(cubicMeter);
	}

	private void persistOrder() {
		em.persist(salesOrder1);
		
	}

	@Test 
	@Ignore
	public void testDelete() {
		logger.info("Test delete");
		em.getTransaction().begin();
		ProductParameter pp = em.find(ProductParameter.class, 5L);
		logger.info("Delete pp" + pp.getId());
		em.remove(pp);
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
		em.persist(pigIron);
		em.persist(hotIron);
		em.persist(steelPlate);
		em.persist(channel);
		em.persist(iBeam);
		em.persist(slab);
		
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
		em.persist(highCarbonIronProduct);
		em.persist(steelProduct); 
				
		em.persist(rolledProduct);
		em.persist(flatRolledProduct);
		em.persist(longRolledProduct);
		
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
		em.persist(amk);
		em.persist(duferco);
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

//		Query query = em.createNamedQuery("partyList");
//		List<?> result = query.getResultList();
//		
//		for (Object object : result) {
//			logger.debug(object);
//		}
		
	}
}
