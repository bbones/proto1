package org.proto1.services;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.proto1.domain.Language;
import org.proto1.domain.product.Product;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@Ignore
@ContextConfiguration(locations={"classpath:/META-INF/domain.xml", "classpath:/META-INF/applicationContext.xml"})
public class RepositoryBehaviorTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	LanguageRepository languageRepository;
	
	@Autowired
	Product steelPlate;
	
	@Autowired
	Language english, russian, ukrainian;
	
	@Test
	public void test() {
		
	
		languageRepository.save(english);
		languageRepository.save(russian);
		languageRepository.save(ukrainian);
	
		steelPlate =productRepository.save(steelPlate);
		
		Product prod = productRepository.getById(steelPlate.getId());
		
		prod.getProductNames().put(ukrainian, "Плита сталева");
		productRepository.save(prod);
		prod = productRepository.getById(steelPlate.getId());
		assertEquals(3, prod.getProductNames().size()); 

	}

}