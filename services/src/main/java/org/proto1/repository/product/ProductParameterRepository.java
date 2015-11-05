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

import org.proto1.domain.product.ProductParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductParameterRepository extends JpaRepository<ProductParameter, Long> {

	List<ProductParameter> getParameterByProductId(Long productId);
	@Query("select new Map(pp.id as productParameterId, pp.product.id as productId, pp.parameter.id as parameterId, pp.required as required, "
			+ "pp.derivative as derivative, ppn.name as parameterName, pp.valueProvider as valueProvider, pp.masterParameter.id as masterParameterId, "
			+ "pp.defaultUOM.id as defaultUOMId, pp.version as version) "
			+ "from ProductParameter pp join pp.parameter.parameterNames ppn "
			+ "where pp.product.id = :product_id and ppn.language.id=:language_id")
	List<Map<String, Object>> getParametersByProductIdLanguageId(@Param("product_id") Long productId, @Param("language_id") Long languageId);
	/**
	 * @param productId
	 * @param parameterId
	 * @return
	 */
	ProductParameter getByProductIdAndParameterId(Long productId,
			Long parameterId);
	List<ProductParameter> findByProductId(Long id);
	

}
