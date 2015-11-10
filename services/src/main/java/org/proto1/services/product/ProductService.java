/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
package org.proto1.services.product;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductName;
import org.proto1.domain.product.ProductParameter;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.product.ProductRepository;

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
	ProductName saveProductName(Long productNameId, Long productId, Long languageId, 
			String productNames, Integer version);

	void deleteName(Long productNameId);

	List<ProductName> getNamesList(Long productId);
	
	// ProductParameter methods
	List<Map<String, java.lang.Object>> getParameterList(Long productId, Long languageId);

	ProductParameter saveProductParameter(ProductParameter productParameter);

	void deleteProductParameter(Long productId);
	
	ProductParameter getProductParameter(Long productId, Long parameterId);
	
	ProductParameter getProductParameter(Long id);
	
}
