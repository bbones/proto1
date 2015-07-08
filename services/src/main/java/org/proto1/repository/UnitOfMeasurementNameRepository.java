/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import java.util.List;

import org.proto1.domain.UnitOfMeasurementName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UnitOfMeasurementNameRepository extends CrudRepository<UnitOfMeasurementName, Long> {
	public UnitOfMeasurementName findByUnitOfMeasurementIdAndLanguageId(Long unitOfMeasurementId, Long languageId);
	@Query("select uomn.shortName as shortName from UnitOfMeasurementName uomn where uomn.unitOfMeasurement.id = :uomId and language.id = :language_id")
	public String getShortName(@Param("uomId") Long unitOfMeasurementId, @Param("language_id") Long languageId);
	public List<UnitOfMeasurementName> findByUnitOfMeasurementId(Long uomId);
}
