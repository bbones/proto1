/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
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
import org.proto1.domain.ApproveType;
import org.proto1.domain.Contract;
import org.proto1.domain.Currency;
import org.proto1.domain.Railway;
import org.proto1.domain.Language;
import org.proto1.domain.SideRole;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.inventory.InventoryLot;
import org.proto1.domain.inventory.Location;
import org.proto1.domain.order.SalesOrder;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.Person;
import org.proto1.domain.product.Parameter;
import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductParameter;
import org.proto1.domain.product.ProductType;
import org.proto1.domain.product.Receipt;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.proto1.domain.valueprovider.GradeSteelStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
  
@ContextConfiguration(locations={
		"classpath:/META-INF/product.xml", 
		"classpath:/META-INF/order.xml",
		"classpath:/META-INF/inventory.xml",
		"classpath:/META-INF/utility.xml", 
		"classpath:/META-INF/receipt.xml",
		"classpath:/META-INF/valueprovider.xml",
		"classpath:/META-INF/approve.xml",
		"classpath:/META-INF/railway.xml"
	})
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
	Currency uah, usd, eur, gbp, jpy, chf, rub;
	
	@Autowired
	Contract contract_sell, contract_buy;
	
	@Autowired
	ProductType 
		ironProduct, highCarbonIronProduct,  
		steelProduct, rolledProduct, flatRolledProduct, longRolledProduct, 
		castedProduct,
		cokeProduct, cokeMainProduct, cokeByProduct,
		iormProduct, nonAgglomeratedIORM, agglomeratedIORM;
	
	@Autowired
	Product 
		pigIron, hotIron, 
		converterSteel, steelPlate, channel, iBeam, slab, scrap, squareBullet,
		coke, cokeBreese, coalTar,
		ironOreConcentrate, ironOre, ironOrePellet, ironOreSinter;
	
	@Autowired
	Receipt
		hotIronReceipt, converterSteelReceipt, slabReceipt, steelPlateReceipt, 
		squareBulletReceipt, iBeamReceipt;
	
	@Autowired
	Parameter
		gradeOfSteel, gradeOfSteelStandard, gradeOfCoke, ironContent, thickness, width, length;
	
	@Autowired
	Language english, russian, ukrainian;
	
	@Autowired
	UnitOfMeasurement notDefinedUOM, metricTonn, cubicMeter, kg, mm, percent;
	
	@Autowired
	LocalizedStringConstant defaultEnglishProductType, defaultRussianProductType, defaultUkrainianProductType;
	
	@Autowired
	SalesOrder hrpSalesOrder1, hrpSalesOrder2, slabSalesOrder1, slabSalesOrder2;
	
	@Autowired
	Location centralInventory, goodsInTransit, oreYard, slabInventory, mariupolPort;
	
	@Autowired
	InventoryLot slabLot1;
	
	@Autowired
	GradeSteelStandard astm, gost;

	@Autowired
	ApproveType contractApproveType;
	
	@Autowired
	Railway rw0, rw1, rw8, rw9, rw12, rw40, rw43, rw48;
		
	@Before
	public void startUp () {

		Map<String, String> properties = new HashMap<String, String>();
		  properties.put("javax.persistence.jdbc.user", "bbones");
		  properties.put("javax.persistence.jdbc.password", "bb");
		emf = Persistence.createEntityManagerFactory("proto1", properties);	
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		persistLanguage();
		persistUnitOfMeasurement();
		persistCurrency();
		persistLocation();
		persistPerson();
		persistEnterprise();
		persistSideRole();
		persistContract();
		persistProductType();
		persistParameters();
		persistProduct();
		persistReceipt();
		persistLocalizedStringConstant();
		
		//persistOrder();
		//persistInventoryLot();
		
		persistStandard();

		persistApproveType();
		persistRailway();
		
		em.flush();
		em.getTransaction().commit();

	}
	
	private void persistStandard() {		
		em.persist(gost);
		em.persist(astm);
	}
	
	private void persistRailway() {		
		em.persist(rw0);
		em.persist(rw1);
		em.persist(rw8);
		em.persist(rw9);
		em.persist(rw12);
		em.persist(rw40);
		em.persist(rw43);
		em.persist(rw48);
	}

	private void persistInventoryLot() {
		em.persist(slabLot1);
	}

	private void persistLocation() {
		em.persist(centralInventory);
		em.persist(goodsInTransit); 
		em.persist(oreYard);
		em.persist(slabInventory);
		em.persist(mariupolPort);		
	}

	private void persistReceipt() {
		em.persist(hotIronReceipt);
		em.persist(converterSteelReceipt); 
		em.persist(slabReceipt);
		em.persist(steelPlateReceipt);		
		em.persist(iBeamReceipt);
		em.persist(squareBulletReceipt);
	}

	private void persistCurrency() {
		em.persist(uah);
		em.persist(usd);
		em.persist(eur);
		em.persist(gbp);
		em.persist(jpy);
		em.persist(chf);
		em.persist(rub);
	}

	private void persistUnitOfMeasurement() {
		em.persist(notDefinedUOM);
		em.persist(kg);
		em.persist(mm);
		em.persist(metricTonn);
		em.persist(cubicMeter);
		em.persist(percent);
	}

	private void persistOrder() {
		em.persist(hrpSalesOrder1);
		em.persist(hrpSalesOrder2);
		em.persist(slabSalesOrder1);
		em.persist(slabSalesOrder2);
		
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
		em.persist(gradeOfSteelStandard);
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
		em.persist(scrap);
		em.persist(converterSteel);
		em.persist(slab);
		em.persist(squareBullet);
		em.persist(steelPlate);
		em.persist(channel);
		em.persist(iBeam);
		
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
		logger.warn("Saving contract..............");
		em.persist(contract_sell);
		em.persist(contract_buy);
		logger.warn("End saving contract..............");
	}

	private void persistSideRole() {
		logger.warn("Saving side role..............");

		em.persist(seller);
		em.persist(buyer);
		logger.warn("End Saving side role..............");
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
	
	private void persistApproveType() {
		em.persist(contractApproveType);
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
