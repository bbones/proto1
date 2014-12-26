package org.proto1.services;

import java.util.Map;

import org.apache.log4j.Logger;
import org.proto1.domain.Language;
import org.proto1.domain.product.Product;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceBean implements ProductService {
	final static Logger logger = Logger.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private LanguageRepository languageRepository;

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public void setLanguageRepository(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
		
	}
	
	public Product getById(Long id) {
		Product product = productRepository.getById(id);
		return product;
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public void delete(Long id) {
		productRepository.delete(id);
	}

	public void saveProductNames(Long productId, Map<Language, String> productNames) {
		Product product = getById(productId);
		product.getProductNames().putAll(productNames);
		productRepository.save(product);
	}

	@Transactional
	public void saveProductName(Long productId, Language language, String productNames) {
		Product product = getById(productId);
		product.getProductNames().put(language, productNames);
		productRepository.save(product);
	}

	public void saveProductName(Long productId, Long languageId,
			String productNames) {
		Language language = languageRepository.findOne(languageId);
		saveProductName(productId, language, productNames);
	}

	public void deleteName(Long productId, Long languageId) {
		Product product = getById(productId);
		Language language = languageRepository.findOne(languageId);

		product.getProductNames().remove(language);
		productRepository.save(product);
		
	}

}
