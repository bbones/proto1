/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.product;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.ProductType;
import org.proto1.domain.product.ProductTypeName;
import org.proto1.repository.product.ProductTypeNameRepository;
import org.proto1.repository.product.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductTypeServiceBean implements ProductTypeService {
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Autowired
	private ProductTypeNameRepository productTypeNameRepository;

	public ProductType get(Long id) {
		return productTypeRepository.findOne(id);
	}

	public void setProductTypeRepository(
			ProductTypeRepository productTypeRepository) {
		this.productTypeRepository = productTypeRepository;
	}

	public List<ProductType> getByParentTypeId(Long id) {
		return productTypeRepository.getByParentTypeId(id);
	}

	public List<Map<String, Object>> getByParentTypeIdByLanguageId(
			Long parentId, Long languageId) {
		if (parentId == null)
			return productTypeRepository.getRootParentTypesLanguageId(languageId);
		else			
			return productTypeRepository.getByParentTypeIdLanguageId(parentId, languageId);
	}

	public int countChild(Long parentId) {
		return productTypeRepository.countChild(parentId);
	}

	public List<ProductTypeName> getNames(Long id) {
		return productTypeRepository.findOne(id).getProductTypeNames();
	}

	@Transactional
	public ProductType save(ProductType productType) {
		return productTypeRepository.save(productType);
	}

	@Transactional
	public void deleteProductTypeById(Long id) {
		productTypeRepository.delete(id);
	}

	public ProductTypeName saveProductTypeName(ProductTypeName productTypeName) {
		return productTypeNameRepository.save(productTypeName);
	}
/*
	public List<Map<String, Object>> getTreeByProductTypeIdByLanguageId(
			Long prodTypeId, Long languageId) {
		List<Map<String, Object>> pt = productTypeRepository.getRootParentTypesLanguageId(languageId);
		Map<String, Object> branch = null;
		Map<String, Object> node = productTypeRepository.getNamedByLanguage(prodTypeId, languageId);
		while(node.get("parentId") != null) {
			if (branch == null) { 
				branch = node;
			} else
				node.put("children", value)
		}
		return pt;
	}
*/
}
