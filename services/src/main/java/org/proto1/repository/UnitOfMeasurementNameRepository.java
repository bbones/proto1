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
