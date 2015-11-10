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
package org.proto1.repository.product;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {
	@EntityGraph(value="Product.productNames", type=EntityGraphType.LOAD)
	public Product getById(Long id);
	
	@Query("select new Map(p.id as productId, p.productType.id as productTypeId , pn.name as productName) " + 
			"from Product p join p.productNames pn " + 
			"where p.productType.id = :pt_id and pn.language.id = :language_id")
	public List<Map<String, Object>> getListByProdTypeIdByLanguageId(@Param("pt_id") Long productTypeId, @Param("language_id") Long languageId);

	@Query("select new Map(p.id as productId, pn.name as productName) " + 
			"from Product p join p.productNames pn " + 
			"where pn.language.id = :language_id")
	public List<Map<String, Object>> getListByLanguageId(@Param("language_id")Long languageId);
	
}
