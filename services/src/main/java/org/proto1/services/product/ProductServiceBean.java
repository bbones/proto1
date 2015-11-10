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

import org.apache.log4j.Logger;
import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductName;
import org.proto1.domain.product.ProductParameter;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.product.ProductNameRepository;
import org.proto1.repository.product.ProductParameterRepository;
import org.proto1.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceBean implements ProductService {
	final static Logger logger = Logger.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductNameRepository productNameRepository;
	
	
	@Autowired
	private ProductParameterRepository productParameterRepository;

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

	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}

	public void delete(Long id) {
		productRepository.delete(id);
	}

	public List<Map<String, Object>> getList(Long productTypeId, Long languageId) {
		return productRepository.getListByProdTypeIdByLanguageId(productTypeId, languageId);
	}

	public List<Map<String, Object>> getList(Long languageId) {
		return productRepository.getListByLanguageId(languageId);
	}

	public ProductName saveProductName(Long productNameId, Long productId,
			Long languageId, String productNames, Integer version) {
		ProductName productName = new ProductName();
		productName.setId(productNameId);
		productName.setProduct(productRepository.findOne(productId));
		productName.setLanguage(languageRepository.findOne(languageId));
		productName.setName(productNames);
		productName.setVersion(version);
		productNameRepository.save(productName);
		
		return productName;
	}

	public void deleteName(Long productNameId) {
		productNameRepository.delete(productNameId);
		
	}

	public List<ProductName> getNamesList(Long productId) {
		return productNameRepository.findByProductId(productId);
	}
	
	public List<Map<String, java.lang.Object>> getParameterList(Long productId, Long languageId) {
		return productParameterRepository.getParametersByProductIdLanguageId(productId, languageId);
	}

	@Transactional
	public ProductParameter saveProductParameter(ProductParameter productParameter) {
		return productParameterRepository.save(productParameter);
	}

	@Transactional
	public void deleteProductParameter(Long productParameterId) {
		productParameterRepository.delete(productParameterId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.product.ProductService#getProductParameter(java.lang.Long, java.lang.Long)
	 */
	public ProductParameter getProductParameter(Long productId, Long parameterId) {
		return productParameterRepository.getByProductIdAndParameterId(productId, parameterId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.product.ProductService#getProductParameter(java.lang.Long)
	 */
	public ProductParameter getProductParameter(Long id) {
		return productParameterRepository.findOne(id);
	}

}
