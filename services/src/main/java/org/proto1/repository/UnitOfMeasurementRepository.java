/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.UnitOfMeasurement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UnitOfMeasurementRepository extends CrudRepository<UnitOfMeasurement, Long> {

	@Query("select new Map(uom.id as uomId, uomn.shortName as uomShortName,"
			+ "uomn.fullName as uomFullName) " + 
			"from UnitOfMeasurement uom join uom.unitOfMeasurementNames uomn " + 
			"where uomn.language.id = :language_id")
	List<Map<String, Object>> getList(@Param("language_id") Long languageId);
}
