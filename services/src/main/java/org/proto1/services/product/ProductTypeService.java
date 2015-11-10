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

import org.proto1.domain.product.ProductType;
import org.proto1.domain.product.ProductTypeName;
import org.proto1.repository.product.ProductTypeRepository;

public interface ProductTypeService {

	ProductType get(Long id);
	List<ProductType> getByParentTypeId(Long id);
	List<Map<String, Object>> getByParentTypeIdByLanguageId(Long parentId, Long languageId);
	
	void setProductTypeRepository(ProductTypeRepository productTypeRepository);
	int countChild(Long parentId);
	List<ProductTypeName> getNames(Long id);
	
	ProductType save(ProductType productType);
	void deleteProductTypeById(Long id);
	ProductTypeName saveProductTypeName(ProductTypeName productTypeName);
//	List<Map<String, Object>> getTreeByProductTypeIdByLanguageId(
//			Long prodTypeId, Long languageId);

}
