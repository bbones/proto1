/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.ProductParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductParameterRepository extends JpaRepository<ProductParameter, Long> {

	List<ProductParameter> getParameterByProductId(Long productId);
	@Query("select new Map(pp.id as productParameterId, pp.product.id as productId, pp.parameter.id as parameterId, pp.required as required, "
			+ "pp.derivative as derivative, ppn.name as parameterName, pp.valueProvider as valueProvider, pp.masterParameter.id as masterParameterId) "
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
	

}
