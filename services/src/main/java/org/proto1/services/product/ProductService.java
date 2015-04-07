/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.product;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductName;
import org.proto1.domain.product.ProductParameter;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.ProductRepository;

public interface ProductService {

	void setProductRepository(ProductRepository productRepository);
	void setLanguageRepository(LanguageRepository languageRepository);

	// Product methods
	Product getById(Long id);

	Product save(Product product);

	void delete(Long id);

	List<Map<String, Object>> getList(Long languageId);

	List<Map<String, Object>> getList(Long productTypeId, Long languageId);

	// ProductName methods
	ProductName saveProductName(Long productNameId, Long productId, Long languageId, String productNames);

	void deleteName(Long productNameId);

	List<ProductName> getNamesList(Long productId);
	
	// ProductParameter methods
	List<Map<String, java.lang.Object>> getParameterList(Long productId, Long languageId);

	ProductParameter saveProductParameter(Long productId, Long parameterId, boolean required);

	void deleteProductParameter(Long productId);
	
	ProductParameter getProductParameter(Long productId, Long parameterId);
	
	ProductParameter getProductParameter(Long id);
	
}
