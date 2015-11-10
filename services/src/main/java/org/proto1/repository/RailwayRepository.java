/*******************************************************************************
 * Copyright (C) 2015   Serhiy Romaniuk 
 *
 * mail:rsk@isd.com.ua
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
import java.util.Map;

import org.proto1.domain.Railway;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RailwayRepository extends CrudRepository<Railway, Long>,
	JpaSpecificationExecutor<Railway> {

	@Query("select new Map(rw.id as id, rw.railwayCode as railwayCode, " +
			"rw.version as version) " +
			"from Railway rw " +
			"order by rw.railwayCode asc")
	List<Map<String, Object>> getList();
	
	@Query("select rw " +
			"from Railway rw " +
			"where rw.railwayCode = :railway_code ")
	Railway findByRailwayCode(@Param("railway_code") Integer railwayCode);

}
