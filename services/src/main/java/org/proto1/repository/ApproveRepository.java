/*******************************************************************************
 * Copyright (C) 2015   Boris Efimenko
 *
 * mail:Boris.Efimenko@isd.com.ua
 * https://github.com/BorisEfimenko
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

import java.util.Date;

import org.proto1.domain.Approve;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ApproveRepository extends CrudRepository<Approve, Long> {

	@Query(" from Approve a where a.approveType.id=:approve_type_id and a.beginDate<=:actual_date and coalesce(a.endDate,'9999-01-01')>:actual_date")
	Approve getActualApprove(@Param("approve_type_id") Long approveTypeID, @Param("actual_date") Date actualDate );

}
