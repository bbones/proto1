/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package org.proto1.repository.product;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.ProductType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long>{

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
