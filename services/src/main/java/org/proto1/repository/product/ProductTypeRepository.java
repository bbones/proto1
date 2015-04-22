/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository.product;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.ProductType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long>{

	@EntityGraph(value="ProductType.productTypeNames", type=EntityGraphType.LOAD)
	public ProductType getById(Long id);
	
	public List<ProductType> getByParentTypeId(Long id);
	
	@Query("select new Map(pt.id as id, pt.parentType.id as parent_id, ptn.name as text) " +
			"from ProductType pt join pt.productTypeNames ptn where pt.parentType.id = :parent_id and ptn.language.id = :language_id")
	public List<Map<String, Object>> getByParentTypeIdLanguageId(@Param("parent_id") Long parentId, @Param("language_id") Long languageId);
	
	@Query("select new Map(pt.id as id, pt.parentType.id as parent_id, ptn.name as text) " +
			"from ProductType pt join pt.productTypeNames ptn where pt.parentType.id is null and ptn.language.id = :language_id")
	public List<Map<String, Object>> getRootParentTypesLanguageId(@Param("language_id") Long languageId);
	
	@Query("select count(pt) from ProductType pt where pt.parentType.id = :parent_id")
	public int countChild(@Param("parent_id") Long parentId);
	
	@Query("select new Map(pt.id as id, ptn.name as name, pt.parentType.id as parentId)"
			+ "from ProductType pt join pt.productTypeNames ptn where ptn.language.id=:language_id and pt.id = :id")
	public Map<String, Object> getNamedByLanguage(@Param("id")Long id,@Param("language_id") Long languageId);

}
