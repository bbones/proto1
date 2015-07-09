/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ParameterRepository extends CrudRepository<Parameter, Long> {
	@Query("select new Map(p.id as parameterId, p.type as parameterType , pn.name as parameterName) " + 
			"from Parameter p join p.parameterNames pn  " + 
			"where pn.language.id = :language_id")
	public List<Map<String, Object>> getList(@Param("language_id") Long languageId);

	@Query("SELECT p FROM Parameter p LEFT JOIN FETCH p.acceptedUOM WHERE p.id = (:id)")
	public Parameter getEagerly(@Param("id") Long id);
	
}
