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
/** Rsk 09.07.2015 */
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.RailwayName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RailwayNameRepository extends CrudRepository<RailwayName, Long> {
	@Query("select new Map(rwn.id as id, rwn.language.id as languageId " +
			",rwn.shortName as shortName, rwn.fullName as fullName " +
			",rwn.railway.id as railwayId, rwn.version as version) " +
			"from RailwayName as rwn " +
			"where rwn.railway.id = :railway_id " +
			"order by rwn.language.id asc")
	List<Map<String, Object>> getList(@Param("railway_id") Long railwayId);
}
