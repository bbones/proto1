package org.proto1.services;

import java.util.Map;

import org.proto1.domain.Language;
import org.proto1.domain.product.Product;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.ProductRepository;

public interface ProductService {

	void setProductRepository(ProductRepository productRepository);

	Product getById(Long id);

	Product save(Product product);

	void delete(Long id);

	void saveProductNames(Long productId, Map<Language, String> productNames);
	
	void saveProductName(Long productId, Long languageId, String productNames);

	void setLanguageRepository(LanguageRepository languageRepository);

	void deleteName(Long productId, Long languageId);

}