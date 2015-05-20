/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.proto1.domain.party.Enterprise;

public interface EnterpriseRepository extends CrudRepository<Enterprise, Long> {

	@Query("select new Map(e.id as id, en.name as name) " + 
			"from Enterprise e join e.enterpriseNames en  " + 
			"where en.language.id = :language_id")
	public List<Map<String, Object>> getListByLanguageId(@Param("language_id") Long languageId);

}
