/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import org.proto1.domain.DimensionUnitName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DimensionUnitNameRepository extends CrudRepository<DimensionUnitName, Long> {
	public DimensionUnitName findByDimensionUnitIdAndLanguageId(Long dimensionUnitId, Long languageId);
	@Query("select dun.shortName as shortName from DimensionUnitName dun where dun.dimensionUnit.id = :duId and language.id = :language_id")
	public String getShortName(@Param("duId") Long dimensionUnitId, @Param("language_id") Long languageId);
}
